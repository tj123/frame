package com.shundian.frame.api.entity.sys;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Setter
@Getter
public class LscrmFunctionPrivilegeTree {

    /**
     * 编号
     **/
    private int id;

    private String createId;

    private String updateId;

    /**
     * 创建时间
     **/
    private Date createTime;

    /**
     * 修改时间
     **/
    private Date updateTime = new Date();

    /**
     * 有效性 1.有效 0. 无效
     **/
    private int validity = 1;

    /**
     * 编码
     **/
    private String code;

    /**
     * 名称
     **/
    private String functionName;

    /**
     * 父节点
     **/
    private int parentId;

    /**
     * 是否叶子节点(叶子结点 就是度为0的结点 就是没有子结点的结点),在添加子节点时,需要将parent_id is_leaf_node 设置成0
     **/
    private int isLeafNode;

    /**
     * 所属子系统
     **/
    private int subSystemId;

    /**
     * UI是否隐藏,ui上不展示
     **/
    private int isHidden = 1;

    private List<LscrmFunctionPrivilegeEntity> privilegeList = new ArrayList<LscrmFunctionPrivilegeEntity>();


}