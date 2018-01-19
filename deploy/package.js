require('shelljs/global');
var tar = require('tar');
var fs = require('fs');

var buld_path = 'build';


var files = {
  'frame-service': ['frame-service/target/*.jar', 'frame-service/target/classes'],
  'frame-web': ['frame-web/target/*.jar']
}

/**
 * 初始
 */
function init() {
  rm('-rf', buld_path);
  mkdir('-p', buld_path);
}

/**
 * 打包
 */
function package(project) {
  var file = files[project];
  if (!file) {
    echo(`没有找到包${project}`);
    exit(1);
  }
  var ph = buld_path + '/' + project + '/';
  mkdir('-p', ph);
  cp('-r', file, ph);
  cd(buld_path);
  tar.c({
      gzip: true
    },
    [project]
  ).pipe(fs.createWriteStream(project + '.tar.gz'));
  cd('..');
  rm('-rf', ph);
}

var args = Object.assign([], process.argv);
args.shift();
args.shift();

init();

for (let arg of args) {
  package(arg);
}



