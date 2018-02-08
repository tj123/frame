require('shelljs/global');
const ejs = require('ejs');
const fs = require('fs');
const path = require('path');

/**
 * 渲染文件
 */
function render(file, data) {
  return new Promise((resolve, reject) => {
    ejs.renderFile(file, data, (err, res)=> {
      if (err) {
        return reject(err);
      }
      resolve(res);
    });
  });
}

/**
 * 复制 project.sh
 */
async function sh(data) {
  const file = data.project + '.sh';
  if (test('-f', file)) {
    console.error(`${file} 文件已存在`);
    exit(1);
  }
  const content = await render(path.join(__dirname, 'template/project.sh'), data);
  fs.writeFileSync(file, content);
}

function checkFile(file) {
  let rtn = null;
  for(const fl of ls(file).toString().split(',')){
    if(/\.jar$/.test(fl)){
      rtn = fl;
      break;
    }
  }
  if(!rtn){
    console.error(`没有找到 ${file} 对应的文件`);
    exit(1);
  }
  return rtn.match(/[^\/\\]+$/).toString();
}

class EjsData {

  constructor(cfg) {
    this.project = cfg.project;
    this.outDir = cfg.outDir;
    this.tmpDir = cfg.tmpDir;
    this.deployDir = cfg.deployDir;
    this.files = {};
    this.init(cfg);
  }

  init(cfg) {
    for(const proj in cfg.files){
      this.files[proj] = checkFile(cfg.files[proj]);
    }
  }

}


module.exports = {
  render: render,
  projectSh: sh,
  EjsData: EjsData
};