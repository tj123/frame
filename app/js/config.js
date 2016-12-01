app.config(['$stateProvider', '$urlRouterProvider',
  function ($stateProvide, $urlRouterProvider) {
    //$urlRouterProvider.otherwise('/t3/dep');
    $urlRouterProvider.otherwise('/dep');
    $stateProvide
        .state('app', {
          url: '/',
          templateUrl: 'app.html',
          resolve: {
            deps: ['$ocLazyLoad', function (ld) {
              return ld.load(['css/app.css']);
            }]
          }
        })
        .state('app.dep', {
          url: 'dep',
          templateUrl: 'tpl/sys_dep.html'
        })
        .state('app.dep2', {
          url: 'dep2',
          templateUrl: 'tpl/sys_dep2.html'
        })
        .state('app.menu', {
          url: 'sys/menu',
          templateUrl: 'tpl/sys_menu.html',
          resolve:{
            deps:['$ocLazyLoad',function (ld) {
              return ld.load(['js/ctrl/sys_menu.js']);
            }]
          }
        })
      .state('app.role', {
        url: 'sys/role',
        templateUrl: 'tpl/sys_role.html',
        resolve:{
          deps:['$ocLazyLoad',function (ld) {
            return ld.load(['js/ctrl/sys_role.js','css/sdgrid.css']);
          }]
        }
      })
  }])


