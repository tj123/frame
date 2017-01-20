(function (angular, $,app) {

  app.controller('LoginCtrl',['$scope','$http','$window','$timeout','$state',function ($scope,$http,$window,$timeout,$state) {

    var user = $scope.user = {};

    $(function () {
      $('#username').focus();
    });

    $scope.nopError = false;

    $scope.submit = function () {
      var password = user.password;
      $scope.nopError = false;
      $http.post('sys/login',{username:user.username,password:hex_md5(password)}).success(function (dt) {
        if(dt.status){
          $window.location.href = './';
        }else {
          $scope.nopError = true;
        }
        $timeout(function () {
          $scope.login.$submitted =false;
          $scope.$apply();
        });
      });
    };



  }]);

})(angular,jQuery,app);