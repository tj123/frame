var tag = require('./tag');




echo(
`# 这个文件由ci工具自动生成
# 请不要随意修改

project.version=${tag.current || tag.next}
`).to('frame-web/src/main/resources/project.properties');


if(!tag.current){
  tag.tag();
}








