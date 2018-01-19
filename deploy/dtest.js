require('shelljs/global');


if(!which('git')){
  echo('没有安装 git');
  exit(1);
}

var tags = exec('git tag').toString().split('\n');
for(var i in tags){
  if(!tags[i]){
    tags.splice(i,1);
  }
}

var branch = exec('git symbolic-ref --short -q HEAD').toString();
if(!branch){
  echo('当前位置位于游离状态');
  exit(1);
}

/**
 * 上一个版本号
 */
function lastRevision(tag){
  if(!tag){
    return '0';
  }
  var tg = tag.split('_')[0];
  return tg.match(/[^\.]$/).toString();
}

var commitId = exec('git rev-parse HEAD').toString();
var currentTag = exec(`git tag --contains ${commitId}`).toString();

var lastTag = tags[tags.length -1];

var tag = currentTag;

if(!tag){

  if(lastTag){

  }

}

echo(lastRevision(lastTag));

// console.log(tags);
// console.log(branch);
// console.log(commitId);
// console.log(currentTag);
// console.log(lastTag);










