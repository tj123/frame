app.controller('SysFunctionCtrl',['$scope','$http','$state',function ($scope,$http,$state) {

    /**
     * 表格的配置
     * @type {{name: string, method: string, url: string}}
     */
    $scope.option = {
        name:'$grid',
        method:'POST',
        url:'sys/func',

    };

    /**
     * 解析 模块数据
     * @param mds
     * @returns {string}
     */
    $scope.parseModule = function (mds) {
        mds.sort(function(md1,md2){
            return md1.id > md2.id;
        });
        var names = [];
        for(var i in mds){
            names.push(mds[i].name);
        }
        return names.join(' , ');
    }

    $scope.edit = function (item) {
        $state.go('app.sys.funcEdit',item);
        console.log($state);

        console.log(item);
    };

    /**
     * 扫描功能
     */
    $scope.scanFunction = function () {
        $http.get('http://localhost/sys/func/scan').then(function () {
            $scope.$grid.loadPage(1);
        });
    }



}]);