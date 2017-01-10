;
(function (angular, $) {

  angular
    .module('gov',[])
    .controller('OtherUserCtrl', ['$scope', '$http','userService','$q','departmentService','$document',
      function ($scope, $http,userService,$q,departmentService,$document) {


        $scope.option = {
          url: 'manage/ouser/sub'
        };

        $scope.deps = {
          source: function (req, resp) {
            $scope.$grid.keywords.departmentid = '';
            typeRecover(allType);
            $scope.$apply();
            $http
              .post('manage/ouser/subdep', {
                keyword: req.term,
                type:$scope.departmentTypeId
              })
              .success(function (d) {
                if (d.state) {
                  resp(d.data);
                }
              })
          },
          select: function (e, ui) {
            $scope.$grid.keywords.departmentid = ui.item.value;
            var ty = ui.item.departmentTypeId;
            for(var i in depType){
              if(i != ty)
                delete depType[i];
            }
            $scope.$apply();
          },
          minLength: 0
        };

        var allType,depType = $scope.depType = {},depTypeSel = $scope.depTypeSel = {};

        /**
         * 高级搜索
         */
        $scope.advancedSearch = function () {
          var keywords = $scope.$grid.keywords;
          var dp = keywords.depType = [];
          for(var i in depTypeSel){
            if(depTypeSel[i])
              dp.push(i);
          }
          $scope.$grid.search();
        };

        /**
         *  恢复类型显示
         * @param tps
         */
        var typeRecover = function (tps) {
          var typs = angular.copy(tps);
          var sel = $scope.depTypeSel;
          for(var i in sel){
            sel[i] = false;
          }
          for(var i in typs){
            depType[typs[i].key] = typs[i];
          }
        };
        departmentService.allType('sdfsdf').then(function (typs) {
          allType  = typs;
          typeRecover(typs);
        });

        $scope.checkType = function () {
          if($.trim($scope.department) == ''){
            typeRecover(allType);
            $scope.$grid.keywords.departmentid = '';
          }
        };

        var list = $scope.list = {};
        var all = $scope.all = {};

        var auth = $scope.auth = function (id) {
          auth.show = true;
          auth.id = id;
          auth.list().then(function(){
            auth.search();
          });

        };
        auth.list =function(){
          var defer = $q.defer();
          userService.auth(auth.id).then(function(d){
            angular.copy(d,original);
            auth.current = d;
            defer.resolve();
          });
          return defer.promise;
        };

        auth.list.selectAll = function(){
          for(var i in list){
            list[i] = auth.list.selectAll_;
          }
        };

        auth.selectAll = function(){
          for(var i in all){
            all[i] = auth.selectAll_;
          }
        };

        /**
         * 初始的权限
         * @type {Array}
         */
        var original = [];

        /**
         * 是否包含
         * @param idx
         * @returns {boolean}
         */
        var contains = function(idx){
          for(var i in auth.current){
            if(auth.current[i].id === idx) return true;
          }
          return false;
        };
        auth.list.add = function(){
          for(var i in all){
            if(all[i] === true){
              for(var j in auth.all){
                if(auth.all[j].id === i){
                  if(!contains(i)) auth.current.push(auth.all[j]);
                  auth.all.splice(j,1);
                }
              }
              all[i] = false;
            }
          }
        };
        auth.list.remove=function(){
          for(var i in list){
            if(list[i] === true){
              for(var j in auth.current){
                if(auth.current[j].id === i){
                  auth.all.push(auth.current[j]);
                  auth.current.splice(j,1);
                }
              }
              list[i] = false;
            }
          }
        };
        auth.search=function(){
          userService.allAuth($scope.listName).then(function(d){
            //auth.all = d;
            var current = auth.current;
            for(var i in current){
              for(var j in d){
                if(current[i].id == d[j].id){
                  d.splice(j,1);
                  break;
                }
              }
            }
            auth.all = d;
          });

        };
        auth.show = false;


        var ctn = function(id,list){
          for(var i in list){
            if(id === list[i].id) return true;
          }
          return false;
        };
        $scope.save = function(){
          var ad = [],rm =[];
          var current = auth.current;
          for(var i in current){
            if(!ctn(current[i].id,original))
              ad.push(current[i].id);
          }
          for(var i in original){
           if(!ctn(original[i].id,current)){
             rm.push(original[i].id);
           }
          }
          var complete = function(){
            auth.show = false;
            auth.current = [];
            sd.toast.info('编辑成功');
          };
          if(ad.length > 0 ){
            complete.ad = 't';
            userService.addAuth(auth.id,ad).then(function(d){
              complete.ad = 'f';
              if(!complete.rm || complete.rm === 'f'){
                complete();
              }
            });
          }
          if(rm.length > 0 ){
            complete.rm = 't';
            userService.removeAuth(auth.id,rm).then(function(d){
              complete.rm = 'f';
              if(!complete.ad || complete.ad === 'f'){
                complete();
              }
            });
          }
          if(rm.length == 0 && ad.length == 0){
            auth.show = false;
            auth.current = [];
          }
        };

      }])
    .controller('OtherUserAddCtrl', ['$scope', 'userService', 'departmentService','$http','$state','$timeout',
      function ($scope, userService, departmentService,$http,$state,$timeout) {

        $scope.smtEn = true;
        $scope.submit = function () {
          var fm = $scope.fm;
          if (fm.$invalid || !validateLoginName.canUse || !$scope.smtEn) {
          } else {
            $scope.smtEn = false;
            $timeout(function(){$scope.smtEn = true;},2000);
            user.accounttypeid = $scope.departmentTypeId;
            user.password = hex_md5(user.password);
            userService.add(user).then(function(){
              sd.toast.info('添加成功');
              $timeout(function(){$state.go('AmanageAouser');},1000);
            },function(){
              sd.toast.error('添加失败');
            });
          }

        };
        var user = $scope.user = {};

        $scope.deps = {
          source: function (req, resp) {
            user.departmentid = '';
            $scope.$apply();
            $http
              .post('manage/ouser/subdep', {
                keyword: req.term,
                type:$scope.departmentTypeId
              })
              .success(function (d) {
                if (d.state) {
                  resp(d.data);
                }
              })
          },
          select: function (e, ui) {
            $scope.department = ui.item.label;
            user.departmentid = ui.item.value;
            $scope.departmentTypeId = ui.item.departmentTypeId;
            $scope.$apply();
          },
          minLength: 0
        };

        var validateLoginName = $scope.validateLoginName = function () {
          validateLoginName.loading = true;
          userService
            .loginNameExist(user.loginname)
            .then(function () {
              validateLoginName.canUse = false;
              validateLoginName.notCanUse = true;
            }, function () {
              validateLoginName.canUse = true;
              validateLoginName.notCanUse = false;
            })
            .finally(function () {
              validateLoginName.loading = false;
            });
        }

        departmentService.allType('sdfsdf').then(function (typs) {
          $scope.departmentTypes = typs;
        })


      }])
    .controller('OtherUserChgPwdCtrl', ['$scope', '$http', '$state', '$stateParams', '$timeout',
      function ($scope, $http, $state, $stateParams, $timeout) {

        $scope.submit = function () {
          if ($scope.fm.$invalid || $scope.fm.$submitted) return;
          $http
            .post('manage/user/chgpwd/', {
              password: hex_md5($scope.password),
              repassword: hex_md5($scope.repassword),
              guid: $stateParams.id
            })
            .success(function (d) {
              if (d.state) {
                sd.toast.info('修改成功');
                $timeout(function () {
                  $state.go('AmanageAouser');
                }, 1000);
              }
            });
        };

      }])
    .controller('OtherUserEditCtrl', ['$scope', '$state', 'userService', 'departmentService', '$stateParams', '$http','$timeout',
      function ($scope, $state, userService, departmentService, $stateParams, $http,$timeout) {

        var user = $scope.user = {};
        $scope.smtEn = true;
        $scope.deps = {
            source: function (req, resp) {
              user.departmentid = '';
              $scope.$apply();
              $http
                .post('manage/ouser/subdep', {
                  keyword: req.term,
                  type:user.accounttypeid
                })
                .success(function (d) {
                  if (d.state) {
                    resp(d.data);
                  }
                })
            },
            select: function (e, ui) {
              user.department = ui.item.label;
              user.departmentid = ui.item.value;
              user.accounttypeid = ui.item.departmentTypeId;
              $scope.$apply();
            },
            minLength: 0
          };


        departmentService.allType($stateParams.id).then(function (d) {
          $scope.departmentTypes = d;
          userService.getById($stateParams.id).then(function (usr) {
            angular.copy(usr,original);
            $.extend(user, usr);
          });
        });

        var original = {};
        $scope.submit = function(){
          if($scope.fm.$invalid  || !$scope.smtEn) return;
          $scope.smtEn = false;
          $timeout(function(){$scope.smtEn = true;},2000);
          var sub = {};
          sub.accountid = user.accountid;
          for(var i in original){
            if(user[i] !== original[i]){
              sub[i] = user[i];
            }
          }
          var chgd = function(){
            for(var i in sub){
              if(i == 'accountid') continue;
              return true;
            }
            return false;
          };
          if(chgd()){
            userService.edit(user).then(function(){
              sd.toast.info('修改成功');
              $timeout(function () {
                $state.go('AmanageAouser');
              }, 1000);
            },function(){
              sd.toast.error('修改失败');
            });
          }else{
            sd.toast.info('没有做修改');
            $timeout(function () {
              $state.go('AmanageAouser');
            }, 1000);
          }
        }

      }])
  /**
   * 对比值是否相同
   */
    .directive('compare', function () {

      return {

        strict: 'AE',
        scope: {
          orgVal: '=compare'
        },
        require: 'ngModel',
        link: function (scp, ele, att, con) {
          con.$validators.compare = function (val) {
            return val == scp.orgVal;
          };
          scp.$watch('orgVal', function () {
            con.$validate();
          });
        }

      };

    });


})(angular, jQuery);



