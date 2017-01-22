;(function (angular) {

  angular.module('validate', [])

    .directive('remoteCheck', ['$http', '$q', function ($http, $q) {

      return {

        strict: 'A',
        require: 'ngModel',
        link: function (scope, el, attr, con) {

          con.$asyncValidators.remoteCheckReject = function (val) {
            var defer = $q.defer();
            $http.post(attr['remoteCheck'], {value: val}).success(function (dt) {
              if (dt.status === true) {
                defer.resolve();
              } else {
                defer.reject();
              }
            });
            return defer.promise;
          };

        }

      };

    }])

    .directive('compare', function () {

      return {

        strict: 'A',
        scope: {
          orgVal: '=compare'
        },
        require: 'ngModel',
        link: function (scp, ele, att, con) {
          con.$validators.compare = function (val) {
            return val == scp.orgVal;
          };
          scp.$watch('orgVal', function () {
            con.$validate();
          });
        }

      };

    })


})(angular);