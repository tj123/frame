require('shelljs/global');
var tar = require('tar');

/**
 * 打包
 */
function packagea(project, buildPath, files) {
  var _pwd = pwd();
  try {
    var file = files[project];
    if (!file) {
      echo(`没有找到包${project}`);
      exit(1);
    }
    var ph = buildPath + '/' + project + '/';
    mkdir('-p', ph);
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
var compress = function (pms, config) {
  const all = config.files;
  let files = {};
  if (!pms || pms.length == 0) {
    files = all;
  } else {
    for (const pm of pms) {
      if (all[pm]) {
        files[pm] = all[pm];
      } else {
        console.error(`找不到${pm}对应配置`);
        exit(1);
      }
    }
  }
  for (var f in files) {
    console.log(`开始打包 ${f}`);
    packagea(f, config.outDir, files);
  }
};


module.exports = compress;
