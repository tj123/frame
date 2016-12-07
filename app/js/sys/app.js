;(function (window, angular, $, undefined) {

  var app = window.app = angular.module('app', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngSanitize',
    'ngTouch',
    'ngStorage',
    //'sdGrid',
    'ui.router',
    //'ui.bootstrap',
    //'ui.load',
    'ui.jq',
    //'ui.validate',
    'oc.lazyLoad'
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
    .controller('AppCtrl', ['$scope', '$localStorage', '$window', '$timeout',
      function ($scope, $localStorage, $window, $timeout) {
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
    .run(['$rootScope', '$state', '$stateParams',
      function ($rootScope, $state, $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;

        $rootScope.$on('$stateChangeStart',
          function (e, toState, toParams, fromState, fromParams) {
            console.log();
            console.log(e.currentScope);
            // if(e.currentScope != null){
            //     e.currentScope.animate = 'fadeInOut';
            //     e.preventDefault();
            // }

          });
      }])
    .constant('JQ_CONFIG', {
      footable: ['js/lib/jquery/footable/footable.all.min.js', 'js/lib/jquery/footable/footable.core.css'],
      sdGrid: []
      //footable: ['js/lib/jquery/footablev3/footable.js', 'js/lib/jquery/footablev3/footable.bootstrap.css']
      //footable: ['js/lib/jquery/footablev2/footable.js', 'js/lib/jquery/footablev2/footable.core.css']
    })
    .config(['$ocLazyLoadProvider', function ($ocLazyLoadProvider) {
      // We configure ocLazyLoad to use the lib script.js as the async loader
      $ocLazyLoadProvider.config({
        debug: false,
        events: true,
        modules: [{
          name: 'ngGrid',
          files: [
            'js/lib/modules/ng-grid/ng-grid.min.js',
            'js/lib/modules/ng-grid/ng-grid.min.css',
            'js/lib/modules/ng-grid/theme.css'
          ]
        }, {
          name: 'ui.select',
          files: [
            'js/lib/modules/angular-ui-select/select.min.js',
            'js/lib/modules/angular-ui-select/select.min.css'
          ]
        }, {
          name: 'angularFileUpload',
          files: [
            'js/lib/modules/angular-file-upload/angular-file-upload.min.js'
          ]
        }, {
          name: 'ui.calendar',
          files: ['js/lib/modules/angular-ui-calendar/calendar.js']
        }, {
          name: 'ngImgCrop',
          files: [
            'js/lib/modules/ngImgCrop/ng-img-crop.js',
            'js/lib/modules/ngImgCrop/ng-img-crop.css'
          ]
        }
          // ,{
          //   name:'sdGrid',
          //   files:['js/drt/sdgrid.js','css/sdgrid.css']
          // }
        ]
      });
    }]);


})(window, window.angular, jQuery, undefined);

