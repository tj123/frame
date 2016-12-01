(function (angular) {
    var app = angular.module('app').directive('sdGrid', ['$timeout', '$window', '$document','$http',
    function ($timeout, $window, $document,$http) {
        return {
            restrict: 'A',
            link: function (scope, el,attr) {
                var option = scope.$eval(attr.sdGrid),
                  table = el.find('table'),
                  thead = table.find('thead'),
                  tbody = table.find('tbody'),
                  columnLen = thead.find('th').length,
                  $gridFoot = table.find('.grid-foot'),
                  trLink = table.hasClass('grid-link'),
                  $grid = scope[option.name||'$grid'] = {},
                  init = function () {
                      $loading.appendTo(tbody);
                      loadPage(1);
                      $gridFoot.parent('td').attr('colspan',columnLen);
                  },
                  $loading = angular.element('<tr style="display: none;">'
                    +'<td class="grid-loading text-center" colspan="'+columnLen+'">'
                    +'<i class="fa fa-refresh"></i>'
                    +'</td>'
                  +'</tr>'),
                  loading =function () {
                      table.removeClass('grid-link');
                      tbody.find('tr').hide();
                      $loading.show();
                  },
                  loadComplete = function () {
                      tbody.find('tr').show();
                      $loading.hide();
                      trLink && table.addClass('grid-link');
                  },
                  error =function () {
                    alert('出错');
                  },
                  loadPage = $grid.loadPage =function (page) {
                       loading();
                      $http.get(app.CONTEXT + option.url,{
                          data:{page:page}
                      }).success(function (dt) {
                          if(dt.state == true){
                              $.extend(true,$grid,dt.data);

                              console.log($grid);
                          }else{

                          }
                        loadComplete();
                      });
                  },
                  go = $grid.go = function (e) {
                    console.log(e.target);
                  },
                  next = $grid.next = function () {

                  },
                  previous = $grid.previous = function () {

                  },
                  first = $grid.first = function () {

                  },
                  last = $grid.last = function () {

                  },
                  moreNext = $grid.moreNext = function () {

                  },
                  morePrevious = $grid.morePrevious =function () {

                  };
                  init();


                console.log($http);
                console.log(tbody);
                console.log(el);
            }
        };
    }]);
})(window.angular);