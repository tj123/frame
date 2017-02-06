(function (angular, app) {


  app.service('depService',['$q','$timeout','$http',function ($q, $timeout, $http) {

    return {

      /**
       * 根据名称搜索部门
       * @param name
       * @param limit
       */
      search:function (name, limit) {
        var defer = $q.defer();
        $http.post('sys/dep/sch',{name:name||'',limit:limit||10}).success(function (dat) {
          if(dat.status){
            defer.resolve(dat.data);
          }else {
            defer.reject();
          }
        });
        return defer.promise;
      }


    };

  }]);



})(angular,app);