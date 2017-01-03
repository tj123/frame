(function (angular, $, app) {
  app.controller('SysRoleCtrl', ['$scope', '$http', function ($scope, $http) {
    
    /**
     * 表格的配置
     * @type {{name: string, method: string, url: string}}
     */
    $scope.option = {
      url: 'sys/role'
    };
    
    var auth = $scope.auth = function (id) {
      console.log(id);
      // $http.post('sys/func/list/all').success(function (dt) {
      //   if(dt.status){
      //     console.log(dt.data);
      //     $('#notAuthTree').jstree({
      //       core:{
      //         data:dt.data,
      //         checkbox: {
      //           keep_selected_style: true
      //         },
      //         plugins: ["wholerow", "checkbox"]
      //       }
      //     });
      //   }
      // });
      // var notAuth = $('#notAuthTree').jstree();
      // notAuth.hide_icons();
      // notAuth.select_all();
    };
  
    /**
     * 校验父节点
     * @param node
     * @param originTree
     * @param targetTree
     */
    var addNode = function (node,originTree,targetTree) {
      //debugger
      console.log(originTree);
      var parents = node.parents;
      for(var i = parents.length - 1;i >=0 ;i--){
        var parent = parents[i];
        if(parent == '#') continue;
        var nd = targetTree.get_node(parent);
        //console.log(nd);
        if(!nd){
          var name = originTree.get_node(parent).text;
          console.log(name);
          console.log(parents[i + 1]);
          
          console.log(targetTree.create_node(parents[i + 1],name));//{id:parent,name:originTree.get_node(parent).text}));
        }
        if(i == 0){
          targetTree.create_node(node.parent,node.text);
        }
        //console.log();
      }
    };
    
    auth.add = function () {
      var notAuthTree = $('#notAuthTree').jstree(),
        authTree = $('#authTree').jstree(),
        notSel = notAuthTree.get_selected();
      for(var i in notSel){
        var node = notAuthTree.get_node(notSel[i]);
        if(node.children.length == 0){
          addNode(node,notAuthTree,authTree);
        }
        //if(node.child)
        //console.log(node);
      }
      //console.log();
    };
    
    auth.remove = function () {
      
    };
  
    $scope.authTreeOption = {
      core: {
        data:[{
          text:'sdfsd',
          children:[{
            text:'sdfd'
          }]
        }],
      },
      checkbox: {
        keep_selected_style: true
      },
      plugins: ["checkbox"]
    };
    
    $scope.treeOption = {
      core: {
        data: {
          url:function (n) {
            return 'http://localhost/sys/func/list/all';
          },
          data:function (dt) {
            console.log(dt);
            return dt.data;
          }
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