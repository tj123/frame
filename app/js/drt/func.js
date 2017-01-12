(function (angular) {

  angular.module('app')

    .directive('func', ['$timeout', '$window', '$document', 'funcService',
      function ($timeout, $window, $document, funcService) {

        return {
          restrict: 'A',
          link: function (scope, el, attr) {
            el.hide();
            var funcs = funcService.getDirect('func', attr);
            var mds = funcService.getDirect('funcMd', attr);
            scope.$on('funcUpdate', function (event, func) {

              /**
               * 指令不为当前
               */
              if (!funcService.isCurrent(funcs, func)){
                /**
                 * 没有 func-md 指令
                 */
                if(mds.length == 0){
                  if(funcService.containsFunc(funcs)) {
                    el.show();
                  }
                }else if(func) {
                  if (funcService.containModules(mds, func.modules)) {
                    el.show();
                  }
                }
              }else{
                if(mds.length == 0){
                  el.show();
                }
              }

            });
          }
        };

      }])

    .directive('funcMd', ['$timeout', '$window', '$document', 'funcService',
      function ($timeout, $window, $document, funcService) {

        return {
          restrict: 'A',
          link: function (scope, el, attr) {
            el.hide();
            var mds = funcService.getDirect('funcMd', attr);
            var funcs = funcService.getDirect('func', attr);
            scope.$on('funcMdUpdate', function (event, func) {

              /**
               * 没有指令 或者 指令 为当前
               */
              if(funcs.length == 0 || funcService.isCurrent(funcs, func)){
                if (func && funcService.containModules(mds, func.modules)) {
                  el.show();
                }
              }
            });
          }
        };

      }])

    .service('funcService', ['$q', '$http', function ($q, $http) {
      /**
       * 权限缓存
       */
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
          for (var i in auths) {
            if (auths[i]['key'] == key) {
              return auths[i];
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
          for (var i in mds) {
            for (var j in modules) {
              if (mds[i] == (angular.isNumber(mds[i]) ? modules[j]['id'] : modules[j]['key'])) {
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
            if (md) mds.push(md);
          }
          return mds;
        },

        /**
         * 判断 funcs 是否为当前
         * @param funcs
         * @param currentFunc
         */
        isCurrent:function (funcs, currentFunc) {
          if(!funcs || ! currentFunc) return false;
          for(var i in funcs){
            if(funcs[i] == currentFunc.key)
              return true;
          }
          return false;
        }

      };
    }]);

})(window.angular);