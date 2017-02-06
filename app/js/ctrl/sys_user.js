(function (angular, $, app) {
  app.controller('SysUserCtrl', ['$scope', '$http', '$state', '$rootScope', function ($scope, $http, $state, $rootScope) {

    /**
     * 表格的配置
     * @type {{name: string, method: string, url: string}}
     */
    $scope.option = {
      url: 'sys/user'
    };


    $scope.update = function () {
      $rootScope.$broadcast('funcUpdate', {hehhehe: 'asdfasdfasd'});
      console.log('update');
    }


  }]);

  app.controller('SysUserAddCtrl', ['$scope', '$http', '$state', '$stateParams','$timeout','depService', function ($scope, $http, $state, $stateParams,$timeout,depService) {

    var user = $scope.user = {};

    $scope.types = [];

    $scope.depLoad = function (val) {
      depService.search(val).then(function (dt) {
        $timeout(function () {
          $scope.deps=dt;
          $scope.$apply();
        });
      });
    };

    $scope.typeSel = function (item, model) {
      console.log(item);
      console.log(model);
    };

    $scope.roleLoad = function (val) {
      depService.search(val).then(function (dt) {
        $timeout(function () {
          $scope.tps=dt;
          $scope.$apply();
        });
      });
    };


    $scope.submit = function () {

      setTimeout(function () {
        $scope.form.$submitted = false;
        $scope.$apply();
      }, 1000);

    };


  }]);



  app.filter('propsFilter', function() {
    return function(items, props) {
      var out = [];

      if (angular.isArray(items)) {
        items.forEach(function(item) {
          var itemMatches = false;

          var keys = Object.keys(props);
          for (var i = 0; i < keys.length; i++) {
            var prop = keys[i];
            var text = props[prop].toLowerCase();
            if (item[prop].toString().toLowerCase().indexOf(text) !== -1) {
              itemMatches = true;
              break;
            }
          }

          if (itemMatches) {
            out.push(item);
          }
        });
      } else {
        // Let the output be the input untouched
        out = items;
      }

      return out;
    };
  })


})(window.angular, jQuery, app);