(function (angular, $, app) {
  app.controller('SysUserCtrl', ['$scope', '$http', '$state','$rootScope', function ($scope, $http, $state,$rootScope) {

    /**
     * 表格的配置
     * @type {{name: string, method: string, url: string}}
     */
    $scope.option = {
      url: 'sys/user'
    };

    var $grid = $scope.$grid;

    
    $scope.update = function () {
      $rootScope.$broadcast('funcUpdate',{hehhehe:'asdfasdfasd'});
      console.log('update');
    }


  }]);

  app.controller('SysUserAddCtrl',['$scope','$http','$state','$stateParams',function ($scope,$http,$state,$stateParams) {

  }]);

})(window.angular, jQuery, app);