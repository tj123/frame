(function (angular, $, undefined) {

  angular.module('imgUploader', []).directive('imgUploader', ['$timeout', '$window', '$document', '$http', '$q', '$rootScope',
    function ($timeout, $window, $document, $http, $q, $rootScope) {
      return {
        restrict: 'A',
        link: function (scope, el, attr) {

          var option = scope.$eval(attr.imgUploader);

          var config = {
            name:'$uploader',
            preview:true,
            subDirectory:null,
            uploadUrl:'sys/file/upload',
            showUrl:'sys/file/get',
            deleteUrl:'sys/file/del',
            maxSize:4
          };

          if(angular.isObject(option)){
            angular.extend(config,option);
          }

          var $uploader = scope[config.name] = {};

          console.log(config);

        }
      };
    }]);

})(window.angular, jQuery);