(function (angular, app) {

  app.service('authService', ['$http', '$q', function ($http, $q) {

    return {

      /**
       * 移动选择的节点
       * @param originTree 从...jstree
       * @param targetTree 目标 jstree
       */
      moveSelect: function (originTree, targetTree) {
        var sel = originTree.get_selected();
        for (var i in sel) {
          var node = originTree.get_node(sel[i]);
          if (node && node.children.length == 0) {
            this.moveNode(node, originTree, targetTree);
          }
        }
      },

      /**
       * 移动指定节点
       * @param node
       * @param originTree
       * @param targetTree
       */
      moveNode: function (node, originTree, targetTree) {
        var parents = node.parents;
        for (var i = parents.length - 1; i >= 0; i--) {
          var parent = parents[i];
          if (parent == '#') continue;
          if (!targetTree.get_node(parent)) {
            var name = originTree.get_node(parent).text;
            targetTree.create_node(parents[i + 1], {id: parent, text: name});
          }
        }
        if (!targetTree.get_node(node.id)) {
          targetTree.create_node(node.parent, {id: node.id, text: node.text});
        }
        originTree.delete_node(node.id);
        for (var i in parents) {
          if (parents[i] == '#') continue;
          if (originTree.get_node(parents[i]).children.length == 0) {
            originTree.delete_node(parents[i]);
          }
        }
      },

      /**
       * 列出所有的权限
       */
      listAll: function (name) {
        var defer = $q.defer();
        $http.post('sys/func/list/all',{name:name}).success(function (dt) {
          if (dt.status) {
            defer.resolve(dt.data);
          } else {
            defer.reject();
          }
        });
        return defer.promise;
      },

      /**
       * 列出所有的权限
       */
      listRole: function (role,name) {
        var defer = $q.defer();
        $http.post('sys/role/lstfunc',{role:role,name:name}).success(function (dt) {
          if (dt.status) {
            defer.resolve(dt.data);
          } else {
            defer.reject();
          }
        });
        return defer.promise;
      },

      /**
       * 为角色添加具有的功能权限
       * @param role 角色id
       * @param funcs 菜单模块数组
       */
      roleAdd: function (role, funcs) {
        var defer = $q.defer();
        if (funcs && funcs.length > 0) {
          $http.post('sys/role/addfunc',{role:role,funcs:funcs}).success(function (dt) {
            if(dt.status){
              defer.resolve(dt.data);
            }else{
              defer.reject();
            }
          });
        }else{
          defer.reject();
        }
        return defer.promise;
      },
  
      /**
       * 为角色添加具有的功能权限
       * @param role 角色id
       * @param funcs 菜单模块数组
       */
      roleRemove: function (role, funcs) {
        var defer = $q.defer();
        if (funcs && funcs.length > 0) {
          $http.post('sys/role/mvfunc',{role:role,funcs:funcs}).success(function (dt) {
            if(dt.status){
              defer.resolve(dt.data);
            }else{
              defer.reject();
            }
          });
        }else{
          defer.reject();
        }
        return defer.promise;
      },
      
      /**
       * 选出所有被选中的子节点
       * @param tree jstree
       */
      listSelectChild: function (tree) {
        var rtn = [], sel = tree.get_selected();
        for (var i in sel) {
          var node = tree.get_node(sel[i]);
          if (node && node.children.length == 0) {
            rtn.push(sel[i]);
          }
        }
        return rtn;
      },

      /**
       * 列出所有的子节点
       * @param tree
       */
      listAllChild:function (tree) {
        var rtn = [], sel = tree.get_node('#').children_d;
        for (var i in sel) {
          var node = tree.get_node(sel[i]);
          if (node && node.children.length == 0) {
            rtn.push(sel[i]);
          }
        }
        return rtn;
      },
  
      /**
       * 列出所有的子节点(基于数据)
       * @param data
       */
      listAllDataChild:function (data) {
        var rtn = [];
        var getData = function (dt) {
          for(var i in dt){
            var children = dt[i]['children'];
            if(children && children.length>0){
              getData(children);
            }else{
              rtn.push(dt[i]['id']);
            }
          }
        };
        getData(data);
        return rtn;
      },

      /**
       * 数字形菜单刷新数据
       * @param tree
       * @param data
       */
      treeRefresh:function (tree, data) {
        tree.settings.core.data = data;
        tree.refresh();
      },
  
      /**
       * 递归移除相同的元素
       * @param target
       * @param data
       */
      removeSameData:function (target, data) {
        var containsId  = function (tgt,id) {
          for(var i in tgt){
            var children = tgt[i]['children'];
            if(children && children.length >0){
              if(containsId(children,id))
                return true;
            }
            if(id === tgt[i]['id'])
              return true;
          }
          return false;
        };
        var remove = function (tgt, dt) {
          for(var i in tgt){
            var children = tgt[i]['children'];
            if(children && children.length >0){
              remove(children,dt);
            }else{
              if(containsId(dt,tgt[i]['id'])){
                tgt.splice(i,1);
              }
            }
          }
        };
        remove(target,data);
        remove(target,data);
        remove(target,data);
        remove(target,data);
        remove(target,data);
        remove(target,data);
        return target;
      },
  
      /**
       * 对比数据
       * @param origin
       * @param current
       */
      compareData:function (origin, current) {
        var rtn = {},ad = rtn.add = [],mv = rtn.remove = [];
        var contains = function (arr, item) {
          for(var i in arr){
            if(arr[i] === item)
              return true;
          }
          return false;
        };
        for(var i in origin){
          if(!contains(current,origin[i]))
            mv.push(origin[i]);
        }
        for(var i in current){
          if(!contains(origin,current[i]))
            ad.push(current[i]);
        }
        return rtn;
      },
  
      /**
       * 对比数据
       * @param role  角色id
       * @param admv  {add:[""],remove:[]}
       */
      addRemoveData:function (role,admv) {
        var defer = $q.defer(),ad = false,mv = false,adi = false,mvi = false;
        if(admv.add.length > 0){
          ad = true;adi = true;
          this.roleAdd(role,admv.add).then(function () {
            adi = false;
            if(!mvi){
              complete();
            }
          },function () {
            error();
          });
        }
        if(admv.remove.length > 0){
          mv =true;mvi = true;
          this.roleRemove(role,admv.remove).then(function () {
            mvi = false;
            if(!adi){
              complete();
            }
          },function () {
            error();
          });
        }
        var complete = function () {
          console.log('完成了');
          defer.resolve();
        };
        var error = function () {
          defer.reject();
          console.log('cuowu');
        };
        if(ad == false && mv == false){
          console.log('没有改变');
          defer.resolve();
        }
        return defer.promise;
      }
    };

  }]);

})(angular, app);