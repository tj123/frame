(function (angular, $, app) {
  app.controller('SysUserCtrl', ['$scope', '$http', '$state', function ($scope, $http, $state) {

    /**
     * 表格的配置
     * @type {{name: string, method: string, url: string}}
     */
    $scope.option = {
      name: '$grid',
      method: 'POST',
      url: 'sys/user'
    };

    var $grid = $scope.$grid;

    /**
     * 解析 模块数据
     * @param mds
     * @returns {string}
     */
    $scope.parseModule = function (mds) {
      mds.sort(function (md1, md2) {
        return md1.id > md2.id;
      });
      var names = [];
      for (var i in mds) {
        names.push(mds[i].name);
      }
      return names.join(' , ');
    };

    /**
     * 扫描功能
     */
    $scope.scanFunction = function () {
      $http.get('sys/func/scan').then(function () {
        $grid.loadPage(1);
      });
    };


  }]);

})(window.angular, jQuery, app);