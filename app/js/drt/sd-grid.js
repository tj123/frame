;(function (angular, $, undefined) {
  angular.module('sd.grid', []).directive('sdGrid', ['$timeout', '$window', '$document', '$http',
    function ($timeout, $window, $document, $http) {
      return {
        restrict: 'A',
        link: function (scope, el, attr) {

          var option = scope.$eval(attr.sdGrid),
            table = el.find('table'),
            thead = table.find('thead'),
            tbody = table.find('tbody'),
            columnLen = thead.find('th').length,
            $gridFoot = el.find('.grid-foot'),
            trLink = table.hasClass('grid-link'),
            $grid = scope[option.name || '$grid'] = {};
          $grid.size = option.size || 8;
          $grid.advanced = false;
          $grid.sortVal = '';
          var keywords = $grid.keywords = {};
          var currentPage = 1, currentSn = 1, totalPage = 1, advanced = false, asc = 'asc';
          var $loading = angular.element('<tr style="display: none;">'
            + '<td class="grid-loading text-center" colspan="' + columnLen + '">'
            + '<i class="fa fa-refresh"></i>'
            + '</td>'
            + '</tr>');
          var $noResult = angular.element('<tr style="display:none;" class="none no-result">'
              + '<td colspan="' + columnLen + '">'
              + '没有相关结果！'
              + '</td>'
              + '</tr>'),
            init = function () {
              $loading.appendTo(tbody);
              $noResult.appendTo(tbody).hide();
              loadPage(1);
              $gridFoot.parent().parent('td').attr('colspan', columnLen);
            };

          var loading = function () {
              table.removeClass('grid-link');
              tbody.find('tr').hide();
              //$loading.css('height', $grid.size * 20 + 'px').css('line-height', $grid.size * 20 + 'px');
              $loading.show();
            },
            loadComplete = function () {
              tbody.find('tr').not('.no-result').show();
              $loading.hide();
              trLink && table.addClass('grid-link');
            },
            error = function (e) {
              console.error('出错');
              console.error(e);
            };
          /**
           * 加载页面
           */
          var loadPage = function (page) {
            if(!$grid.sortVal){
              var stDft = el.find('th.sortable[grid-sort-default]');
              if(stDft.length == 1){
                stDft.trigger('click');
                return;
              }
            }
            $noResult.hide();
            if (page && page > 0) currentPage = page;
            if (!currentPage) currentPage = 1;
            var data = {page: currentPage, size: $grid.size};
            if (!advanced && $grid.keyword) {
              data.keyword = $grid.keyword;
            } else if (advanced && $grid.keywords) {
              var dks = data.keywords = {}, kws = $grid.keywords;
              for (var i in kws) {
                if (angular.isString(kws[i])) {
                  dks[i] = kws[i];
                } else {
                  dks[i] = JSON.stringify(kws[i]);
                }
              }
            }
            if ($grid.sortVal && angular.isString($grid.sortVal)) {
              data.sort = $grid.sortVal;
              data.asc = asc == 'asc' ? 'asc' : 'desc';
            }
            loading();
            $http({url: option.url, method: option.method || 'POST', data: data})
              .success(function (dt) {
                if (dt.status === true) {
                  var d = dt.data;
                  $grid.total = d.total;
                  totalPage = Math.ceil(parseInt(d.total) / parseInt($grid.size));
                  if (currentPage > totalPage) {
                    currentPage = totalPage;
                    if (totalPage != 0) {
                      loadPage();
                      return;
                    }
                  }
                  $grid.rows = d.rows || [];
                  currentSn = (currentPage - 1) * $grid.size;
                  for (var i in $grid.rows) {
                    $grid.rows[i]['$index'] = (currentPage - 1) * $grid.size + parseInt(i) + 1;
                  }
                  if ($grid.rows.length === 0) {
                    $noResult.show();
                  }
                  updateFoot(totalPage, currentPage);
                  if($grid.sortVal == ''){
                    el.find('th.sortable').addClass('sort-disabled');
                  }
                  if ($grid.advanced === true)
                    $grid.advanced = false;
                } else {
                  error();
                }
                loadComplete();
              }).error(function (e) {
              loadComplete();
              error(e);
            });
          };
          /**
           * 指定页面
           * @param e 必须为 event
           */
          $grid.go = function (e) {
            loadPage(parseInt(angular.element(e.target).text()));
          };
          /**
           * 下一页
           */
          $grid.next = function () {
            if (eles.nxt.isDisabled()) return;
            loadPage(++currentPage);
          };
          /**
           * 前一页
           */
          $grid.previous = function () {
            if (eles.pre.isDisabled())return;
            loadPage(--currentPage);
          };
          /**
           * 第一页
           */
          $grid.first = function () {
            loadPage(1);
          };
          /**
           * 最后一页
           */
          $grid.last = function () {
            loadPage(totalPage);
          };
          /**
           * 后 3 页
           */
          $grid.moreNext = function () {
            loadPage(currentPage + 3);
          };
          /**
           * 前 3 页
           */
          $grid.morePrevious = function () {
            loadPage(currentPage - 3);
          };
          /**
           * 刷新页面
           * @type {$grid.search}
           */
          $grid.reload = $grid.refresh = $grid.search = function () {
            advanced = $grid.advanced;
            loadPage(1);
          };
          /**
           * 排序
           * @param sort 名称
           * @param ac 方向
           */
          $grid.sort = function (sort, ac) {
            if (angular.isString(sort)) {
              $grid.sortVal = sort;
            }
            if (ac && angular.isString(ac) && $.trim(ac) != '') {
              asc = ac;
            }
            loadPage(1);
          };
          var footEle = function (el) {
            this[0] = el[0];
          };

          footEle.prototype = {
            constructor: footEle,
            active: function () {
              var e = this;
              angular.element(e[0]).removeClass('disable');
              angular.element(e[0]).addClass('active');
            },
            disable: function () {
              var e = this;
              angular.element(e[0]).removeClass('active');
              angular.element(e[0]).addClass('disable');
            },
            isDisabled: function () {
              return angular.element(this[0]).hasClass('disable');
            },
            enable: function () {
              angular.element(this[0]).removeClass('disable');
            },
            recover: function () {
              var e = this;
              angular.element(e[0]).removeClass('active');
              angular.element(e[0]).removeClass('disable');
            },
            hide: function () {
              angular.element(this[0]).parent().hide();
            },
            show: function () {
              angular.element(this[0]).parent().show();
            },
            text: function (t) {
              angular.element(this[0]).text(t);
            }
          };
          var btns = $gridFoot.find('.btn');
          var eles = {
            pre: new footEle(btns.first()),
            nxt: new footEle(btns.last()),
            num: [],
            morePre: new footEle(btns.eq(2)),
            moreNext: new footEle(btns.eq(btns.length - 3)),
            all: function (fun) {
              var e = this;
              for (var i in e.num) {
                e.num[i][fun]();
              }
              e.morePre[fun]();
              e.moreNext[fun]();
              e.$index = 0;
            },
            $index: 0,
            next: function () {
              var e = this, rtn = e.num[e.$index++];
              if (e.$index >= e.num.length) e.$index = e.num.length - 1;
              return rtn;
            },
            hide: function () {
              this.next().hide();
            },
            show: function (start, stop) {
              var e = this;
              if (stop) {
                for (var i = start; i <= stop; i++) {
                  e.show(i);
                }
              } else {
                if (start < 1) start = 1;
                if (start == 1) e.$index = 0;
                if (start == totalPage) e.$index = e.num.length - 1;
                var next = eles.next();
                next.show();
                next.text(start);
                (start == currentPage) && next.active();
              }
            }
          };

          for (var i = 1; i < btns.length - 1; i++) {
            if ((i == 2) || (i == btns.length - 3))continue;
            eles.num.push(new footEle(btns.eq(i)));
          }

          var updateFoot = function (total, current) {
            eles.all('recover');
            eles.all('hide');
            eles.nxt.enable();
            eles.pre.enable();
            var lend = Math.floor(eles.num.length / 2),
              leftView = function () {
                eles.show(1, eles.num.length - 1);
                eles.moreNext.show();
                eles.show(total);
              },
              rightView = function () {
                eles.show(1);
                eles.morePre.show();
                eles.show(total - eles.num.length + 2, total);
                eles.show(total);
              },
              centerView = function () {
                eles.show(1);
                eles.morePre.show();
                eles.show(current - lend + 1, current + lend);
                eles.moreNext.show();
                eles.show(total)
              },
              allView = function () {
                eles.show(1, total);
              };
            if (total <= eles.num.length + 1) {
              allView();
            } else {
              if (current <= lend + 1) {
                leftView();
              } else if (current > lend + 1 && current < total - lend) {
                centerView();
              } else {
                rightView();
              }
            }
            if (current == 1) {
              eles.pre.disable();
            }
            if (current == total) {
              eles.nxt.disable();
            }
          };

          el.find('th.sortable').bind('click', function (e) {
            var elm = angular.element(this);
            if (elm.hasClass('desc') || !elm.hasClass('asc')) {
              asc = 'asc';
              elm.removeClass('desc').addClass('asc');
            } else {
              asc = 'desc';
              elm.removeClass('asc').addClass('desc');
            }
            var sort = elm.attr('grid-sort').toString();
            if ($.trim(sort) == '') {
              throw new Error('grid-sort 不能为空');
            }
            elm.removeClass('sort-disabled').siblings().addClass('sort-disabled');
            $grid.sort(sort);
          });

          el.find('.advanced-search .search-content-wrapper').bind('click', function (e) {
            e.stopPropagation();
          });

          $document
          /**
           * 阻止事件冒泡
           */
            .bind('click', function (e) {
              var ck = angular.element(e.target);
              if (ck.hasClass('search-label') ||
                ck.hasClass('ui-menu-item')) {
                return;
              }
              if ($grid.advanced) {
                $grid.advanced = false;
                scope.$apply();
              }
            })
            .bind('keydown', function (e) {
              if (e && e.keyCode == 27) {
                if ($grid.advanced) {
                  $grid.advanced = false;
                  scope.$apply();
                }
              } else if (e && e.keyCode == 13) {
                var adv =angular.element('.advanced-search');
                if($grid.advanced){
                  adv.find('.grid-search-btn').trigger('click');
                }else{
                  adv.nextAll('.grid-search-btn').trigger('click');
                }
              } else if (e && e.keyCode == 39) {
                if (!angular.element(e.target).is('input'))
                  $grid.next();
              } else if (e && e.keyCode == 37) {
                if (!angular.element(e.target).is('input'))
                  $grid.previous();
              } else if (e && e.keyCode == 192) {
                $grid.advanced = !$grid.advanced;
                scope.$apply();
              }
            });


          scope.$watch((option.name || '$grid') + '.size', function (newVal, oldVal) {
            if (newVal != oldVal)
              loadPage(Math.ceil(currentSn / $grid.size));
          });

          init();

        }
      };
    }]);
})(window.angular, jQuery);