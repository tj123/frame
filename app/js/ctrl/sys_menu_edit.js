app.controller('SysMenuEditCtrl',['$scope','$http',function ($scope,$http) {

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

    /**
     * 扫描功能
     */
    $scope.scanFunction = function () {
        $http.get('http://localhost/sys/func/scan').then(function () {
            $scope.$grid.loadPage(1);
        });
    }



}]);