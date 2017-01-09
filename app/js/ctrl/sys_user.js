(function (angular, $, app) {
  app.controller('SysUserCtrl', ['$scope', '$http', '$state', function ($scope, $http, $state) {

    /**
     * 表格的配置
     * @type {{name: string, method: string, url: string}}
     */
    $scope.option = {
      url: 'sys/user'
    };

    var $grid = $scope.$grid;



  }]);

  app.controller('SysUserAddCtrl',['$scope','$http','$state','$stateParams',function ($scope,$http,$state,$stateParams) {

  }]);

})(window.angular, jQuery, app);