;(function (window, angular, $, undefined) {

  var app = window.app = angular.module('app', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngTouch',
    'ngStorage',
    'ui.router',
    //'ui.bootstrap',
    //'ui.load',
    'ui.jq',
    //'ui.validate',
    'oc.lazyLoad'
    //'sd.grid'
  ])
    .config(['$controllerProvider', '$compileProvider', '$filterProvider', '$provide', '$httpProvider',
      function ($controllerProvider, $compileProvider, $filterProvider, $provide, $httpProvider) {
        app.controller = $controllerProvider.register;
        app.directive = $compileProvider.directive;
        app.filter = $filterProvider.register;
        app.factory = $provide.factory;
        app.service = $provide.service;
        app.constant = $provide.constant;
        app.value = $provide.value;
        app.CONTEXT = 'http://localhost/';
        //修改 $http 的默认数据提交方式
        $httpProvider.defaults.withCredentials = true;
        $httpProvider.interceptors.push(function ($q) {
          return {
            request: function (config) {
              var contentType = config['headers']['Content-Type'],
                accept = config['headers']['Accept'];
              if ((contentType && /application\/json/i.test(contentType))
                || (/get/i.test(config.method) && /application\/json/i.test(accept) && !/\.html$/.test(config.url) )) {
                config['headers']['Content-Type'] = 'application/x-www-form-urlencoded;charset=UTF-8';
                var url = config.url;
                if (url && (/^http/i.test(url) || /^\//.test(url))) {
                  throw new Error('必须使用相对路径！');
                } else {
                  config.url = app.CONTEXT + url;
                }
                if (/get/i.test(config.method)) {
                  config.params = config.data;
                }
                if (config.transformRequest && config.transformRequest.length > 0) {
                  config.transformRequest = [function (d) {
                    return angular.isObject(d) ? $.param(d) : d;
                  }];
                }
              }
              return config;
            }
          };
        });
      }])
    .controller('AppCtrl', ['$scope', '$localStorage', '$window', '$timeout', '$state',
      function ($scope, $localStorage, $window, $timeout, $state) {
        app.$state = $state;

        // add 'ie' classes to html
        var isIE = !!navigator.userAgent.match(/MSIE/i);
        isIE && angular.element($window.document.body).addClass('ie');
        isSmartDevice($window) && angular.element($window.document.body).addClass('smart');

        function isSmartDevice($window) {
          // Adapted from http://www.detectmobilebrowsers.com
          var ua = $window['navigator']['userAgent'] || $window['navigator']['vendor'] || $window['opera'];
          // Checks for iOs, Android, Blackberry, Opera Mini, and Windows mobile devices
          return (/iPhone|iPod|iPad|Silk|Android|BlackBerry|Opera Mini|IEMobile/).test(ua);
        }

        /**
         *屏幕的处理
         */
        var screen = function (w) {
          w = w || angular.element($window).width();
          return new screen.fn.init(w);
        };

        screen.prototype = screen.fn = {
          constructor: screen,
          init: function (w) {
            this.w = w;
          },
          isXs: function () {
            return this.w < 768;
          },
          isSm: function () {
            return this.w > 768 && this.w < 992;
          },
          isMd: function () {
            return this.w > 992 && this.w < 1200;
          },
          isLg: function () {
            return this.w > 1200;
          }
        };

        screen.fn.init.prototype = screen.fn;

        var setting = $scope.setting = {},
          //顶部导航
          _header = setting.header = {
            fixed: true, //xs 默认固定
            nav: {
              close: false,
              menu: true,
              left: false,
              right: false
            },
            expand: {
              up: false
            }
          },
          _headerMenu = setting.headerMenu = {
            fixed: true, //xs 默认固定
            show: false // xs 默认不显示
          },
          //菜单
          _menu = setting.menu = {
            folded: false,
            show: false,//xs 默认不显示
            fixed: true,// xs 默认固定 其余默认不固定
            docked: false
          },
          //},
          header = $scope.header = {
            nav: {
              //切换导航样式
              to: function (val) {
                var n = _header.nav;
                for (var i in n) {
                  if (val == i) {
                    n[i] = true;
                  } else {
                    n[i] = false;
                  }
                }
              },
              toggle: function () {
                var scn = screen();
                if (scn.isXs()) {
                  menu.toggleShow();
                } else if (scn.isSm() || scn.isMd() || scn.isLg()) {
                  menu.toggleFold();
                }
              }
            },
            expand: {
              toggle: function () {
                _header.expand.up = !_header.expand.up;
              },
              up: function () {
                _header.expand.up = true;
              },
              down: function () {
                _header.expand.up = false;
              }
            }
          },
          //顶部左侧导航
          headerMenu = $scope.headerMenu = {
            fixed: function () {
              _headerMenu.fixed = true;
            },
            mobilizable: function () {
              _headerMenu.fixed = false;
            },
            show: function () {
              _headerMenu.show = true;
            },
            hide: function () {
              _headerMenu.show = false;
            }
          },
          //菜单
          menu = $scope.menu = {
            show: function () {
              //xs 的处理
              header.nav.to('close');
              _menu.show = true;
            },
            toggleShow: function () {
              var t = this;
              if (t.isShow()) {
                t.hide();
              } else {
                t.show();
              }
            },
            toggleFold: function () {
              var t = this;
              if (t.isFolded()) {
                t.expand();
              } else {
                t.folded();
              }
            },
            isShow: function () {
              return _menu.show;
            },
            isHide: function () {
              return !_menu.show;
            },
            hide: function () {
              header.nav.to('menu');
              _menu.show = false;
            },
            isFolded: function () {
              return _menu.folded;
            },
            fold: function () {
              header.nav.to('right');
              _menu.folded = true;
            },
            folded: function () {
              this.fold();
            },
            expand: function () {
              header.nav.to('left');
              _menu.folded = false;
            },
            updateNotXs: function () {
              menu.hide();
              header.expand.down();
              if (menu.isFolded()) {
                header.nav.to('right');
              } else {
                header.nav.to('left');
              }
            },
            updateXs: function () {
              if (menu.isShow()) {
                header.nav.to('close');
              } else {
                header.nav.to('menu');
              }
            },
            mask: {
              hide: function () {
                menu.hide();
              }
            }
          },
          content = $scope.content = {
            mask: {
              click: function () {
                header.expand.down();
              }
            }
          };

        $scope.alert = function (a) {
          alert(a);
        };


        /**
         * 屏幕尺寸变化处理
         */
        var resize = function () {
          var scn = screen();
          if (scn.isSm() || scn.isMd() || scn.isLg()) {
            menu.updateNotXs();
          } else if (scn.isXs()) {
            menu.updateXs();
          }
        };
        resize.isRun = false;
        angular.element($window).on('resize', function () {
          if (resize.isRun)return;
          resize.isRun = true;
          $timeout(function () {
            resize();
            resize.isRun = false;
          }, 400);
        });
        resize();

        /**
         * 设置的保存
         */
        if (angular.isDefined($localStorage.setting)) {
          //对象的引用一定要注意 angular.extend() angular.copy() 不支持深拷贝
          var isMatch = true, lsts = $localStorage.setting;
          for (var i in setting) {
            lsts[i] || (isMatch = false);
          }
          isMatch && $.extend(true, setting, lsts);
          isMatch || ($localStorage.setting = setting);
        } else {
          $localStorage.setting = setting;
        }

        $scope.$watch('setting', function () {
          $localStorage.setting = setting;
        }, true);

      }])
    .run(['$rootScope', '$state', '$stateParams', 'funcService', '$timeout',
      function ($rootScope, $state, $stateParams, funcService, $timeout) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
        funcService.loadAuths();
        var currentFunc;
        $rootScope.$on('$stateChangeSuccess',
          function (e, toState, toParams, fromState, fromParams) {
            $timeout(function () {
              currentFunc = funcService.funcUiSref(toState.name);
              $rootScope.$broadcast('funcUpdate', currentFunc);
              $rootScope.$broadcast('funcMdUpdate', currentFunc);
            }, 50);
          });
        $rootScope.$on('sdGridLoadComplete', function () {
          $timeout(function () {
            $rootScope.$broadcast('funcUpdate', currentFunc);
            $rootScope.$broadcast('funcMdUpdate', currentFunc);
          }, 50);
        });
      }])
    .config(['$ocLazyLoadProvider', 'MODULE_LIB', function ($ocLazyLoadProvider, MODULE_LIB) {
      $ocLazyLoadProvider.config({debug: true, events: true, modules: MODULE_LIB});
    }]);


})(window, window.angular, jQuery);

