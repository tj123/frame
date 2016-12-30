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


    $scope.treeOption = {
      core: {
        data: [{
          text: "Root node",
          children: [{
            text: "Child node 1"
          }, {
            text: "Child node 2"
          }]
        },{
          text:'hehe node',
          children:[{
            text:'我要你在我身旁'
          },{
            text:'这夜的风儿吹'
          }]
        },{
          text:'hehe node',
          children:[{
            text:'我要你在我身旁'
          },{
            text:'这夜的风儿吹'
          }]
        },{
          text:'hehe node',
          children:[{
            text:'我要你在我身旁'
          },{
            text:'这夜的风儿吹'
          }]
        },{
          text:'hehe node',
          children:[{
            text:'我要你在我身旁'
          },{
            text:'这夜的风儿吹'
          }]
        },{
          text:'hehe node',
          children:[{
            text:'我要你在我身旁'
          },{
            text:'这夜的风儿吹'
          }]
        },{
          text:'hehe node',
          children:[{
            text:'我要你在我身旁'
          },{
            text:'这夜的风儿吹'
          }]
        },{
          text:'hehe node',
          children:[{
            text:'我要你在我身旁'
          },{
            text:'这夜的风儿吹'
          }]
        },{
          text:'hehe node',
          children:[{
            text:'我要你在我身旁'
          },{
            text:'这夜的风儿吹'
          }]
        },{
          text:'hehe node',
          children:[{
            text:'我要你在我身旁'
          },{
            text:'这夜的风儿吹'
          }]
        },{
          text:'我的啊是'
        },{
          text:'我的啊是'
        },{
          text:'我的啊是'
        },{
          text:'我的啊是'
        }],
        // multiple:false,
        // animation:1
      },
      checkbox: {
        keep_selected_style: true
      },
      plugins: ["wholerow", "checkbox"]
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