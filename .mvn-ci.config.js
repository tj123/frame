var config = {
  project: 'farm',
  outDir: 'build',
  version: {
    file: 'frame-web/src/main/resources/project.properties',
    content: function (ver) {
      return `# 这个文件由ci工具自动生成
# 请不要随意修改

project.version=${ver}
`;
    }
  },
  files: {
    // 'frame-service': ['frame-service/target/*.jar', 'frame-service/target/classes'],
    'frame-service': ['frame-service/target/*.jar'],
    'frame-web': ['frame-web/target/*.jar']
  },
  configs: [{
    env: 'prod',
    user: 'root',
    tmpDir: '/tmp/asfasdgasefdd',
    middleServer: '192.168.1.22',
    targetServer: '192.168.1.25',
    deployDir: '/yuanben/frame'
  }, {
    env: 'test',
    user: 'root',
    tmpDir: '/tmp/asfasdgasefdd',
    middleServer: '192.168.1.22',
    targetServer: '192.168.1.25',
    deployDir: '/yuanben/frame'
  }]
};

module.exports = config;

