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
      if(id)currentRole = id;
      authService.listAll($scope.allKeyword).then(function (all) {
        authService.listRole(currentRole,$scope.keyword).then(function (role) {
          authService.removeSameData(all,role);
          authService.treeRefresh($('#notAuthTree').jstree(true),allData = all);
          authService.treeRefresh($('#authTree').jstree(true),roleData = role);
        });
      });
    };
    
    auth.add = function () {
      authService.moveSelect($('#notAuthTree').jstree(true), $('#authTree').jstree(true));
    };

    auth.remove = function () {
      authService.moveSelect($('#authTree').jstree(true), $('#notAuthTree').jstree(true));
    };

    auth.save = function () {
      var treeData = authService.listAllChild($('#authTree').jstree(true));
      var originData = authService.listAllDataChild(roleData);
      var rtn = authService.compareData(originData,treeData);
      authService.addRemoveData(currentRole,rtn).then(function () {
        $('#authCancel').trigger('click');
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
        data:[],
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