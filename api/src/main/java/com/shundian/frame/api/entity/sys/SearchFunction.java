package com.shundian.frame.api.entity.sys;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索出的模块
 */
@Setter
@Getter
public class SearchFunction implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String id;

    private String name;

    private String parentId;

    private List<SearchFunction> children;

    private List<SearchFunctionModule> modules;

}
