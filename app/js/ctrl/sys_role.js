app.controller('SysRoleCtrl',['$scope',function ($scope) {


	$scope.option = {
		name:'$grid',
		method:'POST',
		url:'sys/menu',

	};


	var gd = $scope.gd = {};

	gd.row = ['1','2','3','4'];




}]);