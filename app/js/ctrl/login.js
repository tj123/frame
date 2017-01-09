(function (angular, $,app) {

  app.controller('LoginCtrl',['$scope','$http','$window',function ($scope,$http,$window) {

    var user = $scope.user = {};

    $(function () {
      $('#username').focus();
    });

    $scope.submit = function () {
      var password = user.password;
      $http.post('sys/login',{username:user.username,password:hex_md5(password)}).success(function (dt) {
        if(dt.status){
          $window.location.href = './';
        }
        console.log(dt);
      });
    };



  }]);

})(angular,jQuery,app);