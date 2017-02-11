package com.shundian.frame.api.entity.sys;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TJ on 2017/1/11.
 */
@Setter
@Getter
public class AuthFunction implements Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private String key;

    private String name;

    private String uiSref;
    
    private List<AuthFunctionModule> modules;

}
