require('shelljs/global');
var tag = require('./tag');


module.exports = function (dir) {

  echo(
    `# 这个文件由ci工具自动生成
# 请不要随意修改

project.version=${tag.current || tag.next}
`).to(dir);

}











