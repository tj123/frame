(function (angular, $, app) {
  
  app.controller('SysDepCtrl', ['$scope', '$http', '$state', function ($scope, $http, $state) {

    /**
     * 表格的配置
     * @type {{name: string, method: string, url: string}}
     */
    $scope.option = {
      url: 'sys/user'
    };

    var $grid = $scope.$grid;



  }]);

})(window.angular, jQuery, app);