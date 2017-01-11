(function (angular) {
  
  angular.module('app')
    
    .directive('func', ['$timeout', '$window', '$document', 'permService',
      function ($timeout, $window, $document, permService) {
      
        return {
          restrict: 'A',
          link: function (scope, el, attr) {
            el.hide();
            var funcs = permService.getDirect('func',attr);
            scope.$on('funcUpdate', function (event, func) {
              var contain = false;
              for(var i in funcs){
                if(funcs[i] === func.key || funcs[i] === func.id){
                  contain = true;
                  break;
                }
              }

              if (permService.containsFunc(funcs)) {
                el.show();
              }
              
            });
            
          }
        };
        
      }])
    
    .directive('funcMd', ['$timeout', '$window', '$document','permService',
      function ($timeout, $window, $document,permService) {
      
        return {
          restrict: 'A',
          link: function (scope, el, attr) {
            el.hide();
            var mds = permService.getDirect('funcMd',attr);
            var funcs = permService.getDirect('func',attr);
            scope.$on('funcMdUpdate', function (event, func) {
              var contain = false;
              for(var i in funcs){
                if(funcs[i] === func.key || funcs[i] === func.id){
                  contain = true;
                  break;
                }
              }
              if(!contain){
                if(permService.containModules(mds,func.modules)){
                  el.show();
                }
              }
            });
          }
        };
        
      }]);
  
})(window.angular);