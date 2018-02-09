require('shelljs/global');
const NodeSsh = require('node-ssh');

const ssh = new NodeSsh();


const checkDir = dir => {
  return `if [ ! -d ${dir} ]; then
  if [ -f ${dir} ]; then
    rm -rf ${dir}
  fi
  mkdir -p ${dir}
fi`;
};

const delExists = file => {
  return `if [ -f ${file} ]; then
  rm -rf ${file}
fi`;
};

async function upload(pms, config, env,password) {
  let files = [];
  if (!pms || pms.length == 0) {
    for (const file in config.files) {
      files.push(file);
    }
  } else {
    files = pms;
  }
  const tmpDir = config.tmpDir;
  const buildDir = config.outDir;
  let ecfg = null;
  for (const cfg of config.configs) {
    if (cfg.env == env) {
      ecfg = cfg;
      break;
    }
  }
  if (!ecfg) {
    throw new Error(`找${env}不到对应的配置`);
  }
  const sshCfg = {};
  sshCfg.host = ecfg.middleServer;
  sshCfg.username = ecfg.user;
  if(password){
    ecfg.password = password;
  }
  if (ecfg.password) {
    sshCfg.password = ecfg.password;
  } else {
    sshCfg.privateKey = '~/.ssh/id_rsa';
  }
  await ssh.connect(sshCfg);
  await ssh.exec(checkDir(tmpDir));
  for (const file of files) {
    const fl = buildDir + '/' + file + '.tar.gz';
    const rfl = tmpDir + '/' + file + '.tar.gz';
    if (!test('-f', fl)) {
      throw new Error(`找不到文件 ${fl}`);
    }
    console.log(`开始上传 ${fl} 到${{test:'测试',prod:'正式'}[env]||env}环境`);
    await ssh.exec(delExists(rfl));
    await ssh.putFile(fl, rfl);
  }
  const fl = config.project + '.sh';
  const rfl = tmpDir + '/' + config.project + '.sh';
  if (!test('-f', fl)) {
    throw new Error(`找不到文件 ${fl}`);
  }
  console.log(`开始上传 ${fl} 到${{test:'测试',prod:'正式'}[env]||env}环境`);
  await ssh.exec(delExists(rfl));
  await ssh.putFile(fl, rfl);
  ssh.dispose();
};


module.exports = {
  upload: upload
};