app.controller('SysMenuEditCtrl', ['$scope', '$http', '$state', '$stateParams', function ($scope, $http, $state, $stateParams) {

	var func = $scope.func = {};

	$http.post('sys/func/list',{
		id: $stateParams.id
	})
		.success(function (d) {
			if(d.status){
				$.extend(func,d.data);
			}
		});


}]);