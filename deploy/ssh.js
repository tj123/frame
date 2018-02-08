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

module.exports = async function () {
  try {
    const tmpDir = '/tmp/asdfasdfasdfasdfasfasdf';
    const buildDir = pwd() + '/build';
    await ssh.connect({
      host: '192.168.1.25',
      username: 'root',
      password: 'yuanben'
      // privateKey: '~/.ssh/id_rsa'
    });

    await ssh.exec(checkDir(tmpDir));
    for (const file of ls('-A', buildDir).toString().split(',')) {
      const fl = buildDir + '/' + file;
      const rfl =tmpDir + '/' + file;
      console.log(`开始上传 ${file}`);
      if (!test('-f', fl)) {
        continue;
      }
      await ssh.exec(delExists(rfl));
      await ssh.putFile(fl, rfl);
    }
    ssh.dispose();
  } catch (e) {
    console.error(e);
    exit(1);
  }
};
