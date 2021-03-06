package com.shundian.frame.api.entity.sys;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 功能模块对象
 */
@Setter
@Getter
public class AuthFunctionModule implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String key;

    private String name;

}
