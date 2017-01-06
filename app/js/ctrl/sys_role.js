(function (angular, $, app) {
  app.controller('SysRoleCtrl', ['$scope', '$http', 'authService', function ($scope, $http, authService) {

    /**
     * 表格的配置
     * @type {{name: string, method: string, url: string}}
     */
    $scope.option = {
      url: 'sys/role'
    };

    var currentRole,allData,roleData;

    var auth = $scope.auth = function (id) {
      currentRole = id;
      authService.listRole(currentRole,$scope.keyword).then(function (role) {
        authService.treeRefresh($('#authTree').jstree(true),role);
      });
      authService.listAll().then(function (all) {
        authService.treeRefresh($('#notAuthTree').jstree(true),all);
      });
    };

    auth.add = function () {
      var notAuthTree = $('#notAuthTree').jstree(true),
        authTree = $('#authTree').jstree(true);
      authService.moveSelect(notAuthTree, authTree);
    };

    auth.remove = function () {
      var notAuthTree = $('#notAuthTree').jstree(true),
        authTree = $('#authTree').jstree(true);
      authService.moveSelect(authTree, notAuthTree);
    };

    auth.save = function () {
      authService.roleAdd(currentRole, authService.listAllChild($('#authTree').jstree(true)));
    };

    auth.searchAll = function () {
      authService.listAll($scope.allKeyword).then(function (all) {
        authService.treeRefresh($('#notAuthTree').jstree(true),all);
      });
    };

    auth.search = function () {
      authService.listRole(currentRole,$scope.keyword).then(function (role) {
        authService.treeRefresh($('#authTree').jstree(true),role);
      });
    };

    $scope.authTreeOption = {
      core: {
        check_callback: true,
        data: function () {
        },
      },
      checkbox: {
        keep_selected_style: true
      },
      plugins: ["checkbox"]
    };

    $scope.treeOption = {
      core: {
        check_callback: true,
        data: function () {
        },
      },
      checkbox: {
        keep_selected_style: true
      },
      plugins: ["checkbox"]
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

    $scope.submit = function () {

      $http.post('sys/role/add', role)
        .success(function (d) {
          if (d.status) {
            //$state.go('app.sys.role');
          } else {
            if (d.error) {
              var e = d.error;
              for (var i in e) {
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