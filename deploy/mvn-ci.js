#!/usr/bin/env node

require('shelljs/global');
var ci = require('commander');

function range(val) {
  return val.split('..').map(Number);
}

function list(val) {
  return val.split(',');
}

function collect(val, memo) {
  memo.push(val);
  return memo;
}

function increaseVerbosity(v, total) {
  return total + 1;
}

ci
  .version('0.1.0')
  .usage('[options] <params ...>')
  // .option('-i, --integer <n>', 'An integer argument', parseInt)
  // .option('-f, --float <n>', 'A float argument', parseFloat)
  // .option('-r, --range <a>..<b>', 'A range', range)
  // .option('-l, --list <items>', 'A list', list)
  // // .option('-o, --optional [value]', 'An optional value')
  // // .option('-c, --collect [value]', 'A repeatable value', collect, [])
  // .option('-v, --verbose', 'A value that can be increased', increaseVerbosity, 0);


// ci.option('-c, --clean [item]','清除编译产生的文件',(val,mem) => {
//   mem.push(val);
//   return mem;
// },[]);

// ci.option('-C, --compress', '指定要压缩的文件');

// ci.option('-p, --package', '打包');

// ci.command('rmdir <dir> [otherDirs...]')
//   .action(function (dir, otherDirs) {
//     console.log('rmdir %s', dir);
//     console.log(otherDirs);
//     console.log(ci.env);
//     // if (otherDirs) {
//     //   otherDirs.forEach(function (oDir) {
//     //     console.log('rmdir %s', oDir);
//     //   });
//     // }
//   });


ci.option('-o, --outDir [dir]', '指定生成文件所在目录,使用相对路径', 'build');
ci.option('-e, --env [value]', '指定环境 (test,prod)');

ci.command('clean [projects...]')
  .description('清除指定子工程,没有参数清除所用')
  .action(pjs => {
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
  .option('-c, --compress', '是否压缩文件')
  .action((pms,options) => {
    var cmd = 'mvn ';
    if (ci.env) {
      cmd += '-P' + ci.env;
    }
    // exec(cmd + 'package');

    if(options.compress === true){

    }

  });
// ci.arguments('<cmd> [env]')
//   .action(function (cmd, env) {
//     console.log(cmd);
//     console.log(env);
//
//   });


// ci.option('*','asdfasd',env => {
//
//   console.log(env);
// });

ci.parse(process.argv);

// console.log(ci.clean);

// console.log(' int: %j', ci.integer);
// console.log(' float: %j', ci.float);
// console.log(' optional: %j', ci.optional);
// ci.range = ci.range || [];
// console.log(' range: %j..%j', ci.range[0], ci.range[1]);
// console.log(' list: %j', ci.list);
// console.log(' collect: %j', ci.collect);
// console.log(' verbosity: %j', ci.verbose);
// console.log(' args: %j', ci.args);