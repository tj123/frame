;(function (app) {
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
        .state('app.sys', {
          url: 'sys',
          templateUrl: 'sys.html'
        })
        .state('app.dep', {
          url: 'dep',
          templateUrl: 'tpl/sys_dep.html'
        })
        .state('app.dep2', {
          url: 'dep2',
          templateUrl: 'tpl/sys_dep2.html'
        })
        .state('app.sys.func', {
          url: '/func',
          templateUrl: 'tpl/sys_func.html',
          resolve: {
            deps: ['$ocLazyLoad', function (ld) {
              return ld.load(['js/ctrl/sys_func.js', 'css/sdgrid.css']);
            }]
          }
        })
        .state('app.sys.funcEdit', {
          url: '/func/edit/:id',
          templateUrl: 'tpl/sys_func_edit.html',
          resolve: {
            deps: ['$ocLazyLoad', function (ld) {
              return ld.load(['js/ctrl/sys_func.js']);
            }]
          }
        })
        .state('app.sys.role', {
          url: '/role',
          templateUrl: 'tpl/sys_role.html',
          resolve: {
            deps: ['$ocLazyLoad', function (ld) {
              return ld.load(['js/ctrl/sys_role.js', 'css/sdgrid.css']);
            }]
          }
        })
        .state('app.sys.roleAdd', {
          url: '/role/add',
          templateUrl: 'tpl/sys_role_add.html',
          resolve: {
            deps: ['$ocLazyLoad', function (ld) {
              return ld.load(['js/ctrl/sys_role.js']);
            }]
          }
        })
        .state('app.sys.user', {
          url: '/user',
          templateUrl: 'tpl/sys_user.html',
          resolve: {
            deps: ['$ocLazyLoad', function (ld) {
              return ld.load(['js/ctrl/sys_user.js', 'css/sdgrid.css']);
            }]
          }
        })
        .state('app.role', {
          url: '/role',
          templateUrl: 'tpl/sys_role.html',
          resolve: {
            deps: ['$ocLazyLoad', function (ld) {
              return ld.load(['js/ctrl/sys_role.js', 'css/sdgrid.css']);
            }]
          }
        })
    }])
})(app);


