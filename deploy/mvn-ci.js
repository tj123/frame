#!/usr/bin/env node

require('shelljs/global');
var ci = require('commander');

ci.version('0.1.0')
  .usage('[options] <params ...>');

ci.option('-o, --outDir [dir]', '指定生成文件所在目录,使用相对路径', 'build');
ci.option('-e, --env [value]', '指定环境 (test,prod)');

ci.command('clean [projects...]')
  .description('清除指定子工程,没有参数清除所用')
  .action(pjs => {
    console.log('开始清除工程');
    exec('mvn clean');
    if (!pjs || pjs.length == 0) {
      rm('-rf', ci.outDir + '/*');
    } else {
      for (var ph of pjs) {
        rm('-rf', ci.outDir + '/' + ph + '*');
      }
    }
  });

ci.command('package [projects...]')
  .description('打包指定子工程,没有参数打包所有')
  .option('-c, --compress [value]', '是否压缩文件','true')
  .action((pms,opt) => {
    var cmd = 'mvn ';
    if (ci.env) {
      cmd += '-P' + ci.env;
    }
    exec(cmd + 'package');
    if(opt.compress == 'true'){
      require('./compress')(pms,ci.outDir);
    }
  });

ci.command('version')
  .description('从git读取版本信息 更新记录到 properties 文件')
  .option('-p, --path [value]','properties文件所在路径','frame-web/src/main/resources/project.properties')
  .action((opt) => {
    require('./version')(opt.path);
  });

ci.command('tag')
  .description('用git给当前 commit 打一个tag')
  .action(() => {
    var tag = require('./tag');
    if(!tag.current){
      tag.tag();
    }else {
      console.log(`tag ${tag.current} 已经存在`);
    }
  });

ci.command('pushTag')
  .description('把tag推到服务器上面')
  .option('-u, --user [value]','git 用户名')
  .option('-p, --password [value]','git 密码')
  .action((opt) => {
    var tag = require('./tag');
    tag.push(opt.user,opt.password);
  });

ci.parse(process.argv);
