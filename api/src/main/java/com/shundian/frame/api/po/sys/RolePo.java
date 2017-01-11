package com.shundian.frame.api.po.sys;

import com.shundian.frame.api.dto.sys.RoleDto;
import com.shundian.frame.api.envm.UserRoleType;
import com.shundian.lib.common.bean.BasePo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Table(name = "tsys_role")
public class RolePo extends BasePo<RoleDto> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String name;

    private String entryFunctionId;

    private UserRoleType bindUserRole;

    private Date operateTime;

}