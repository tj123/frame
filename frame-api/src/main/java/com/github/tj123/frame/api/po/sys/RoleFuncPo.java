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

@Setter
@Getter
@ToString
@Table(name = "s_role_func")
public class RoleFuncPo implements Po<RoleDto>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String roleId;

    private String funcId;


}
