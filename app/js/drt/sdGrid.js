(function (angular, $, app, undefined) {
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
						$grid = scope[option.name || '$grid'] = {};
					$grid.size = option.size || 5;
					var currentPage = 1,currentSn = 1,
						totalPage = 1,
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
						/**
						 * 加载页面
						 * @type {$grid.loadPage}
						 */
						val, sort,
						loadPage = $grid.loadPage = function (page, value, sortAsc) {
							val = value;
							sort = sortAsc;
							if (angular.isString(val)) data.keyword = val;
							if (angular.isObject(val)) data.keywords = val;
							if (sort) data.sort = sort;
							if (page && page > 0) currentPage = page;
							if (!currentPage) currentPage = 1;
							var data = {page: currentPage, size: $grid.size};
							loading();
							$http({
								url: option.url,
								method: option.method || 'POST',
								data: data
							}).success(function (dt) {
								if (dt.status === true) {
									var d = dt.data;
									$grid.rows = d.rows || [];
									$grid.total = d.total;
									totalPage = Math.ceil(parseInt(d.total)/parseInt($grid.size));
									if(currentPage >= totalPage)
										currentPage = totalPage;
									currentSn = (currentPage - 1) * $grid.size;
									updateFoot();
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
							if($next.hasClass('disable'))
								return;
							loadPage(++currentPage);
						},
						previous = $grid.previous = function () {
							if($previous.hasClass('disable'))
								return;
							loadPage(--currentPage);
						},
						first = $grid.first = function () {
							loadPage(1);
						},
						last = $grid.last = function () {
							loadPage(totalPage);
						},
						moreNext = $grid.moreNext = function () {
							loadPage(currentPage + 3);
						},
						morePrevious = $grid.morePrevious = function () {
							loadPage(currentPage - 3);
						},
						reload = $grid.reload = $grid.refresh = $grid.search = function (val,sort) {
							loadPage(undefined,val,sort);
						},
						$previous = $gridFoot.find('.previous'),
						$first = $gridFoot.find('.first'),
						$morePre = $gridFoot.find('.more-pre'),
						$pages = $gridFoot.find('.page'),
						$moreNext = $gridFoot.find('.more-next'),
						$last = $gridFoot.find('.last'),
						$next = $gridFoot.find('.next'),
						$all = $gridFoot.find('.btn'),
						updateFoot = function () {
							$last.text(totalPage);
							$all.show().removeClass('active').removeClass('disable').parent().show();
							if(currentPage == 1){
								$previous.addClass('disable');
								$first.addClass('active');
							}
							if(currentPage == totalPage){
								$next.addClass('disable');
								$last.addClass('active');
							}
							if(currentPage <= 2){
								$morePre.parent().hide();
								$pages.last().parent().hide();
							}
							if(currentPage >= totalPage - 1){
								$moreNext.parent().hide();
								$pages.first().parent().hide();
							}
							if((currentPage >2) && (currentPage < totalPage - 1)){
								$pages.first().parent().hide();
								$pages.last().parent().hide();
							}
							if(totalPage < $pages.length + 4){

							}
						};

					/**
					 *更新条数显示
					 */
					var pages = function(jq,page,total){
						var pg = jq.find('.foot .pages');
						pg.empty();
						var pre = $('<a href="javascript:void(0);" class="previous">&lt;上一页</a>').appendTo(pg);
						if(page == 1){
							pre.addClass('disable');
						}else{
							pre.on('click',function(){
								load(jq,page - 1);
							});
						}
						var show = function(start,stop,p){
							if(stop){
								for(var i = start ;i<=stop;i++){
									var a = $('<a href="javascript:void(0);" class="page"></a>').text(i).appendTo(pg);
									if(i != p){
										a.on('click',function(){
											load(jq,$(this).data('pg'));
										}).data('pg',i);
									}else{
										a.addClass('active');
									}
								}
							}else{
								$('<a href="javascript:void(0);" class="page"></a>').text(start).appendTo(pg).on('click',function(){
									load(jq,$(this).data('pg'));
								}).data('pg',start);
							}
						};
						var showMore = function(){
							$('<span class="more">...</span>').appendTo(pg);
						};
						if( total <= PAGE_DIVIDE + 1 || page <= PAGE_DIVIDE -3){
							if(total > PAGE_DIVIDE +1){
								show(1,PAGE_DIVIDE,page);
								showMore();
								show(total);
							}else{
								show(1,total,page);
							}
						}else if(page > PAGE_DIVIDE-3 && page < total -PAGE_DIVIDE + 3){
							show(1);
							showMore();
							if(page + 3 >= total){
								show(page - 3,total,page);
							}else {
								show(page - 3,page +3,page);
								showMore();
								show(total);
							}
						}else if(page >= total - PAGE_DIVIDE + 3){
							show(1);
							showMore();
							show(total-PAGE_DIVIDE,total,page);
						}
						var nxt = $('<a href="javascript:void(0);" class="next">下一页&gt;</a>').appendTo(pg);
						if(page == total){
							nxt.addClass('disable');
						}else{
							nxt.on('click',function(){
								load(jq,page + 1);
							});
						}

					};

					init();
					scope.$watch((option.name || '$grid') + '.size', function () {
						loadPage(Math.ceil(currentSn/$grid.size));
					});


					console.log($.param({a: 'sdfsdf', "bs": 'sdfsdfsdf'}));

					console.log($http);
					console.log(tbody);
					console.log(el);
				}
			};
		}]);
})(window.angular, jQuery, app);