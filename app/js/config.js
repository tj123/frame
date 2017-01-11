;(function (app) {
  app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvide, $urlRouterProvider) {
    //$urlRouterProvider.otherwise('/t3/dep');
    $urlRouterProvider.otherwise('/sys/user');
    $stateProvide
      .state('app', {
        url: '/',
        templateUrl: 'app.html',
        // resolve: {
        //   deps: ['$ocLazyLoad', function (ld) {
        //     return ld.load(['css/app.css']);
        //   }]
        // }
      })
      .state('app.sys', {
        url: 'sys',
        template: '<ui-view></ui-view>'
      })
      .state('app.sys.dep', {
        url: '/dep',
        templateUrl: 'tpl/sys_dep.html',
        resolve:{
          deps:['$ocLazyLoad',function (ld) {
            return ld.load('sd.grid').then(function () {
              return ld.load('js/ctrl/sys_dep.js');
            });
          }]
        }
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
            return ld.load('sd.grid').then(function () {
              return ld.load('js/ctrl/sys_func.js');
            });
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
            return ld.load('sd.grid').then(function () {
              return ld.load(['js/ctrl/sys_role.js','js/svrs/auth.js']);
            });
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
            return ld.load('sd.grid').then(function () {
              return ld.load(['js/ctrl/sys_user.js']);
            });
          }]
        }
      })

      .state('app.sys.userAdd', {
        url: '/user/add',
        templateUrl: 'tpl/sys_user_add.html',
        resolve: {
          deps: ['$ocLazyLoad', function (ld) {
            return ld.load(['js/ctrl/sys_user.js']);
          }]
        }
      })

      .state('login',{
        url:'/login',
        templateUrl:'login.html',
        resolve:{
          deps:['$ocLazyLoad',function (ld) {
            return ld.load(['js/ctrl/login.js','js/lib/md5.js']);
          }]
        }
      })

      .state('app.noperm',{
        url:'noperm',
        templateUrl:'tpl/noperm.html'
      })

  }])
})(app);


