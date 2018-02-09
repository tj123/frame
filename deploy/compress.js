require('shelljs/global');
var tar = require('tar');


var files = {
  // 'frame-service': ['frame-service/target/*.jar', 'frame-service/target/classes'],
  'frame-service': ['frame-service/target/*.jar'],
  'frame-web': ['frame-web/target/*.jar']
}


/**
 * 打包
 */
function packagea(project, buildPath) {
  var _pwd = pwd();
  try {
    var file = files[project];
    if (!file) {
      echo(`没有找到包${project}`);
      exit(1);
    }
    var ph = buildPath + '/' + project + '/';
    mkdir('-p', ph);
    if(!test('-f',file)){
      console.error(`文件 ${file} 不存在`);
      exit(1);
    }
    cp('-r', file, ph);
    cd(buildPath);
    tar.c({
      gzip: true, sync: true,
      file: project + '.tar.gz',
    }, [project]);
    cd(_pwd);
    rm('-rf', ph);
  } catch (e) {
    cd(_pwd);
  }
}


/**
 * 先解析 ci 输入的参数
 */
var compress = function (pms, outDir) {
  if (pms && pms.length > 0) {
    files = {};
    for (var i in pms) {
      if (i % 2 == 0) {
        var dirs = [];
        if (pms[i + 1]) {
          dirs = pms[i + 1].split(',');
        }
        files[pms[i]] = dirs;
      }
    }
  }
  for (var f in files) {
    console.log(`开始打包 ${f}`);
    packagea(f, outDir);
  }
};


module.exports = compress;
