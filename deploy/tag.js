require('shelljs/global');

function exes(cmd) {
  return exec(cmd, {silent: true}).toString().trim();
}

if (!which('git')) {
  echo('没有安装 git');
  exit(1);
}

var tags = exes('git tag').split('\n');
for (var i in tags) {
  if (!tags[i]) {
    tags.splice(i, 1);
  }
}

var branch = exes('git symbolic-ref --short -q HEAD');
if (!branch) {
  echo('当前位置位于游离状态');
  exit(1);
}

/**
 * 获取版本号
 */
function revision(tag) {
  if (!tag) {
    return;
  }
  var tg = tag.split('_')[0];
  return tg.match(/[^\.]$/).toString();
}

/**
 * 下一个版本号
 */
function nextRevision(rev) {
  if (!rev) {
    return '0';
  }
  if (/^\d+$/.test(rev)) {
    return (parseInt(rev) + 1).toString();
  } else if (/\d+$/.test(rev)) {
    return rev.replace(/\d+$/, val => {
      parseInt(val) + 1;
    }).toString();
  } else {
    return rev + '0';
  }
}

var commitId = exes('git rev-parse HEAD');
var currentTag = exes(`git tag --contains ${commitId}`);

var lastTag = tags[tags.length - 1];

function newVersion() {
  var v = 'v0.0';
  if (/\.x$/.test(branch)) {
    if (lastTag) {
      v = 'v' + branch.replace('x', nextRevision(revision(lastTag))).toString();
    } else {
      v = 'v' + branch.replace('x', '0');
    }
  } else {
    v += '.0';
  }
  var suf = undefined;
  if (lastTag) {
    suf = lastTag.split('_')[1];
  }
  if (!suf) {
    suf = 'base';
  }
  return v + '_' + suf;
}

/**
 * 打tag
 *
 * @param comment
 */
function createTag(comment) {
  var cmd = 'git tag';
  if (comment) {
    cmd += `  -a ${newVersion()} -m ${comment}`;
  } else {
    cmd += ` ${newVersion()}`
  }
  return exes(cmd);
}

/**
 * 推到 origin
 * @returns {*}
 */
function pushTag() {
  return exec('git push origin --tags');
}

module.exports = {
  current: currentTag,
  next: newVersion(),
  tag: createTag,
  push: pushTag
}
