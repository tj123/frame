;(function (angular, $, app) {
  
  app
    .config(['$httpProvider', function ($httpProvider) {
      
      $httpProvider.interceptors.push(function () {
        
        return {
          
          request: function (config) {
            return config;
          },
          
          response: function (resp) {
            
            var headers = resp.headers();
            if (/application\/json/i.test(headers['Content-Type'] || headers['content-type'])) {
              var dat = resp.data, $state = app.$state;
              if (dat.errorCode == '901' || dat.errorCode == '903') {
                $state.go('login');
                console.log('u=' + encodeURIComponent(window.location.href));
              } else if (dat.errorCode == '902') {
                $state.go('app.noperm');
              } else if (dat.errorCode == '908') {
                window.location.hash = '#' + dat.url;
              }
            }
            
            return resp;
          }
        }
        
      });
      
      
    }])
    .service('permService', ['$q', '$http', function ($q, $http) {
      var auths;
      return {
        
        /**
         * 加载用户权限
         * @returns {Promise}
         */
        loadAuths: function () {
          var defer = $q.defer();
          $http.post('sys/auths').success(function (dt) {
            if (dt.status) {
              defer.resolve(auths = dt.data);
            } else {
              defer.reject();
            }
          });
          return defer.promise;
        },
        
        /**
         * 获取模块
         * @param uiSref
         * @returns {*}
         */
        funcUiSref: function (uiSref) {
          for (var i in auths) {
            if (auths[i]['uiSref'] == uiSref) {
              return auths[i];
            }
          }
          return null;
        },
        
        /**
         * 获取指定功能
         * @param key
         * @returns {*}
         */
        func: function (key) {
          if (angular.isString(key)) {
            for (var i in auths) {
              if (auths[i]['key'] == key) {
                return auths[i];
              }
            }
          } else if (angular.isNumber(key)) {
            for (var i in auths) {
              if (auths[i]['id'] == key) {
                return auths[i];
              }
            }
          }
          return null;
        },
        
        /**
         * 是否包含有
         * @param funcs
         * @returns {boolean}
         */
        containsFunc: function (funcs) {
          if (!funcs) return false;
          for (var i in funcs) {
            var func = this.func(funcs[i]);
            if (func)
              return true
          }
          return false;
        },
        
        /**
         * mds 是否包含在module中
         * @param mds
         * @param modules
         */
        containModules: function (mds, modules) {
          if (!mds || !modules) return false;
          for(var i in mds){
            for(var j in modules){
              if(mds[i] == (angular.isNumber(mds[i])? modules[j]['id']:modules[j]['key'])){
                return true;
              }
            }
          }
          return false;
        },
        
        /**
         * 根据名称获取指令元素
         * @param name
         * @param attr
         * @returns {Array}
         */
        getDirect: function (name, attr) {
          var md = attr[name], mds = [];
          if (/^'.*'$/.test(md) || /^".*"$/.test(md)) {
            mds.push(eval(md));
          } else if (/^\[.*\]$/.test(md)) {
            mds = eval(md);
          } else if (/,/.test(md)) {
            mds = md.split(',');
          } else {
            if(md) mds.push(md);
          }
          return mds;
        }
      };
    }])
  
  
})(angular, jQuery, app);