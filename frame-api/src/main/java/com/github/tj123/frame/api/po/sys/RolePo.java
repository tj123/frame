package com.github.tj123.frame.api.po.sys;

import com.github.tj123.common.Po;
import com.github.tj123.frame.api.dto.sys.RoleDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@ToString
@Table(name = "s_role")
public class RolePo implements Po<RoleDto>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String name;

    private String description;

    private String createById;

    private String updateById;

    private Date createTime;

    private Date updateTime;

}
