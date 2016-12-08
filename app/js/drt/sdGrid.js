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
					var currentPage = 1, currentSn = 1,
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
									totalPage = Math.ceil(parseInt(d.total) / parseInt($grid.size));
									if (currentPage >= totalPage)
										currentPage = totalPage;
									currentSn = (currentPage - 1) * $grid.size;
									updateFoot(totalPage,currentPage);
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
							//if (eles.nxt.isDisabled())
								//return;
							loadPage(++currentPage);
						},
						previous = $grid.previous = function () {
							//if (eles.pre.isDisabled())
								//return;
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
						reload = $grid.reload = $grid.refresh = $grid.search = function (val, sort) {
							loadPage(undefined, val, sort);
						};
					var footEle = function (el) {
						this[0] = el[0];
						this.atv = true;
						this.dis = true;
					};

					footEle.prototype = {
						constructor: footEle,
						active: function () {
							var e = this;
							//if (e.dis) {
								angular.element(e[0]).removeClass('disable');
								e.dis = false;
							//}
							//if (!e.atv) {
								angular.element(e[0]).addClass('active');
								e.atv = true;
							//}
						},
						disable: function () {
							var e = this;
							//if (e.atv) {
								angular.element(e[0]).removeClass('active');
								e.atv = false;
							//}
							//if (!e.dis) {
								angular.element(e[0]).removeClass('disable');
								e.dis = true;
							//}
						},
						isDisabled: function () {
							return this.dis;
						},
						recover: function () {
							var e = this;
							//if (e.atv) {
								angular.element(e[0]).removeClass('active');
								e.atv = false;
							//}
							//if (e.dis) {
								angular.element(e[0]).removeClass('disable');
								e.dis = false;
							//}
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
						moreNext: new footEle(btns.eq(btns.length - 2)),
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
						show: function (start, stop) {
							var e = this;
							if (stop) {
								for (var i = start; i <= stop; i++) {
									e.show(i);
								}
							} else {
								if(start < 1) start = 1;
								if (start == 1) e.$index = 0;
								if (start == totalPage) e.$index = e.num.length - 1;
								var next = eles.next();
									next.show();
									next.text(start);
									(start == currentPage) && next.active();
							}
						}
					};

					for(var i =1;i<btns.length -1;i++){
						if((i == 2) || (i == btns.length - 3))continue;
						eles.num.push(new footEle(btns.eq(i)));
					}

					var updateFoot = function (total,current) {
						eles.all('recover');
						eles.all('hide');
						rightView(current,total);
					}

					var lend = Math.round(eles.num.length/2);


					var leftView = function (current,total) {
						eles.show(1, eles.num.length );
						eles.moreNext.show();
						eles.show(total);
					};

					var rightView = function (current,total) {
						eles.show(1);
						eles.morePre.show();
						eles.show(total - eles.num.length, total);
						eles.show(total);
					}

					var centerView = function (current,total) {
						// if(current == 5)
						// 	debugger
						eles.show(1);
						eles.morePre.show();
						eles.show(current - lend,current + lend);
						eles.moreNext.show();
						eles.show(total)
					}


					var updateFoot1 = function (total,current) {
						eles.all('recover');
						eles.all('hide');
						//debugger
						if (current <= lend) {
							if (total > eles.num.length  + 1) {
								eles.show(1, eles.num.length );
								eles.moreNext.show();
								eles.show(total);
							} else {
								eles.show(1, total);
							}
						} else if ((current > eles.num.length - lend) &&
							(current < total - eles.num.length - lend)) {
							if (total > eles.num.length + 1) {
								eles.show(1, eles.num.length);
								eles.moreNext.show();
								eles.show(total);
							} else {
								show(1, total);
							}
						} else if (current >= total - eles.num.length + 3) {
							eles.show(1);
							eles.morePre.show();
							eles.show(total - eles.num.length, total);
						}
					};


					init();
					scope.$watch((option.name || '$grid') + '.size', function () {
						loadPage(Math.ceil(currentSn / $grid.size));
					});


					// console.log($.param({a: 'sdfsdf', "bs": 'sdfsdfsdf'}));
					//
					// console.log($http);
					// console.log(tbody);
					// console.log(el);
				}
			};
		}]);
})(window.angular, jQuery, app);