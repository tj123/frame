package com.github.tj123.frame.api.dto.sys;

import com.github.tj123.common.Dto;
import com.github.tj123.frame.api.po.sys.RolePo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by TJ on 2017/9/20.
 */
@Setter
@Getter
@ToString
public class RoleDto implements Dto<RolePo>, Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String description;

    private String createById;

    private String updateById;

    private String createTime;

    private String updateTime;

}
