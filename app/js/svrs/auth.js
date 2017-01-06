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
       * 数字形菜单刷新数据
       * @param tree
       * @param data
       */
      treeRefresh:function (tree, data) {
        tree.settings.core.data = data;
        tree.refresh();
      }
    };

  }]);

})(angular, app);