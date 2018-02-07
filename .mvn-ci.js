





var cfg = {};



var testConfig = {
  env: 'test',
  user:'root',
  tmpDir:'/tmp/asfasdgasefdd',
  middleServer:'192.168.1.22',
  targetServer:'192.168.1.25',
  deployDir:'/yuanben/frame'
};

var prodConfig = {
  env: 'prod',
  user:'root',
  tmpDir:'/tmp/asfasdgasefdd',
  middleServer:'192.168.1.22',
  targetServer:'192.168.1.25',
  deployDir:'/yuanben/frame'
};




cfg.test = testConfig;
cfg.prod = prodConfig;

module.exports = cfg;

