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
            .state('app.sys.menu', {
                url: '/menu',
                templateUrl: 'tpl/sys_menu.html',
                resolve: {
                    deps: ['$ocLazyLoad', function (ld) {
                        return ld.load(['js/ctrl/sys_menu.js', 'css/sdgrid.css']);
                    }]
                }
            })
            .state('app.sys.menuEdit', {
                url:'/menu/edit',
                templateUrl:'tpl/sys_menu_edit.html'
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
    }])


