(function (angular, $, app) {
  var app = angular.module('app').directive('sdGrid', ['$timeout', '$window', '$document', '$http',
    function ($timeout, $window, $document, $http) {
      return {
        restrict: 'A',
        link: function (scope, el, attr) {
          var option = scope.$eval(attr.sdGrid),
            table = el.find('table'),
            thead = table.find('thead'),
            tbody = table.find('tbody'),
            columnLen = thead.find('th').length,
            $gridFoot = table.find('.grid-foot'),
            trLink = table.hasClass('grid-link'),
            $grid = scope[option.name || '$grid'] = {},
            size = option.size || 10,
            init = function () {
              $loading.appendTo(tbody);
              loadPage(1);
              $gridFoot.parent().parent('td').attr('colspan', columnLen);
            },
            $loading = angular.element('<tr style="display: none;">'
              + '<td class="grid-loading text-center" colspan="' + columnLen + '">'
              + '<i class="fa fa-refresh"></i>'
              + '</td>'
              + '</tr>'),
            loading = function () {
              table.removeClass('grid-link');
              tbody.find('tr').hide();
              $loading.show();
            },
            loadComplete = function () {
              tbody.find('tr').show();
              $loading.hide();
              trLink && table.addClass('grid-link');
            },
            error = function (e) {
              console.error('出错');
              console.error(e);
            },
            loadPage = $grid.loadPage = function (page) {
              loading();
              $http({
                url: option.url,
                method: option.method || 'POST',
                data: {
                  page: page,
                  size: size
                }
              }).success(function (dt) {
                if (dt.status == true) {
                  $.extend(true, $grid, dt.data);

                  console.log($grid);
                } else {
                  error();
                }
                loadComplete();
              }).error(function (e) {
                loadComplete();
                error(e);
              });
            },
            go = $grid.go = function (e) {
              loadPage(parseInt(angular.element(e.target).text()));
            },
            next = $grid.next = function () {

            },
            previous = $grid.previous = function () {

            },
            first = $grid.first = function () {
              loadPage(1);
            },
            last = $grid.last = function () {

            },
            moreNext = $grid.moreNext = function () {

            },
            morePrevious = $grid.morePrevious = function () {

            };
          init();


          console.log($.param({a: 'sdfsdf', "bs": 'sdfsdfsdf'}));

          console.log($http);
          console.log(tbody);
          console.log(el);
        }
      };
    }]);
})(window.angular, jQuery, app);