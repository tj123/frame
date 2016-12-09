(function (angular, $, app) {
  app.controller('SysRoleCtrl', ['$scope', '$http', function ($scope, $http) {

    /**
     * 表格的配置
     * @type {{name: string, method: string, url: string}}
     */
    $scope.option = {
      url: 'sys/role'
    };

    $scope.auth = function (id) {
      console.log(id);
    };


  }]);

  app.controller('SysRoleAddCtrl', ['$scope', '$http', '$state', '$stateParams', function ($scope, $http, $state, $stateParams) {

    var role = $scope.role = {};

    // $http.post('sys/func/list',{
    // 	id: $stateParams.id
    // })
    // 	.success(function (d) {
    // 		if(d.status){
    // 			$.extend(func,d.data);
    // 		}
    // 	});

    $scope.submit = function(){

      $http.post('sys/role/add',role)
        .success(function (d) {
          if(d.status){
            //$state.go('app.sys.role');
          }else{
            if(d.error){
              var e = d.error;
              for(var i in e){
                console.log($scope.form);
              }
            }
            console.error(d);
          }
        });

    };


    $scope.canl = function (d) {
      console.log(d);
      console.log($scope.form);
    }


  }]);

})(window.angular, jQuery, app);