#!/usr/bin/env node

require('shelljs/global');
const ci = require('commander');
const path = require('path');
const _ = require('lodash');

ci.version('0.1.0')
  .usage('[options] <params ...>');

ci.option('-o, --outDir [dir]', '指定生成文件所在目录,使用相对路径', 'build');
ci.option('-e, --env [value]', '指定环境 (test,prod)');
ci.option('-cfg, --config [value]', '指定配置文件', '.mvn-ci.config.js');

var config = null;
try {
  config = require(pwd() + '/' + ci.config);
} catch (e) {

}

if (!config && !/init/.test(process.argv.join(''))) {
  console.error(`找不到配置文件 ${ci.config}`);
  exit(1);
}

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
  .option('-c, --compress [value]', '是否压缩文件', 'true')
  .action((pms, opt) => {
    var cmd = 'mvn ';
    if (ci.env) {
      cmd += '-P' + ci.env;
    }
    exec(cmd + 'package');
    if (opt.compress == 'true') {
      require('./compress')(pms, ci.outDir);
    }
  });

ci.command('version')
  .description('从git读取版本信息 更新记录到 properties 文件')
  .option('-p, --path [value]', 'properties文件所在路径', 'frame-web/src/main/resources/project.properties')
  .action((opt) => {
    require('./version')(opt.path);
  });

ci.command('tag')
  .description('用git给当前 commit 打一个tag')
  .action(() => {
    var tag = require('./tag');
    if (!tag.current) {
      tag.tag();
    } else {
      console.log(`tag ${tag.current} 已经存在`);
    }
  });

ci.command('push')
  .description('把tag推到服务器上面')
  .option('-u, --user [value]', 'git 用户名')
  .option('-p, --password [value]', 'git 密码')
  .option('-r, --remote [value]', '要推送的服务器', 'origin')
  .action((opt) => {
    var tag = require('./tag');
    tag.push(opt.user, opt.password, opt.remote);
  });

ci.command('ssh')
  .description('ssh链接测试')
  .action(()=> {
    require('./ssh')();
  });

ci.command('init')
  .description('初始化配置文件')
  .action(()=> {
    if (test('-f', ci.config)) {
      console.log(`文件 ${ci.config} 已存在`);
    } else {
      cp(path.join(__dirname, 'template/.mvn-ci.config.js'), ci.config);
    }
  });

ci.command('create <file...>')
  .description('根据配置生成初始化文件: sh (shell 脚本 ) yml (gitlab ci 初始化文件)')
  .action(async(pms, opt) => {
    try {
      const render = require('./render');
      if (-1 != _.indexOf(pms, 'sh')) {
        await render.projectSh(new render.EjsData(config));
      }
    } catch (e) {
      console.error(e);
      exit(1);
    }
  });

ci.parse(process.argv);


