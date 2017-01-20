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

  app.controller('SysUserAddCtrl', ['$scope', '$http', '$state', '$stateParams', function ($scope, $http, $state, $stateParams) {

    var user = $scope.user = {};


    $scope.submit = function () {

      console.log($scope.form);

      setTimeout(function () {
        $scope.form.$submitted = false;
        $scope.$apply();
      },1000);

    };

  }]);


  app.directive('compare', function () {

    return {

      strict: 'AE',
      scope: {
        orgVal: '=compare'
      },
      require: 'ngModel',
      link: function (scp, ele, att, con) {

        con.$validators.compare = function (val) {



          return val == scp.orgVal;
        };
        scp.$watch('orgVal', function () {
          con.$validate();
        });
      }

    };

  });


  app.directive('remoteCheck', ['$http','$q', function ($http,$q) {

    return {

      strict:'A',
      scope:{
        url:'@remoteCheck'
      },
      require:'ngModel',
      link:function (scope, el, attr, con) {

        con.$asyncValidators.remoteCheckReject = function (val) {
          var defer = $q.defer();


          setTimeout(function () {
            if(Math.random() > 0.5){
              defer.resolve();
            }else {
              defer.reject('asdfasdf');
            }

          },2000);



          return defer.promise;
        };

      }

    };

  }]);


})(window.angular, jQuery, app);