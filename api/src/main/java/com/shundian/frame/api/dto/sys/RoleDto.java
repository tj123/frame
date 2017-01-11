package com.shundian.frame.api.dto.sys;

import com.shundian.frame.api.po.sys.RolePo;
import com.shundian.lib.common.bean.BaseDto;
import com.shundian.lib.common.bean.convert.BlankCurrent;
import com.shundian.lib.common.bean.validate.NotBlank;
import com.shundian.lib.common.bean.validate.Validate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Validate
public class RoleDto extends BaseDto<RolePo> {

    @NotBlank
    private String name;

    private String entryFunctionId;

    //@InEnum(UserRoleType.class)
    private String bindUserRole;

    @BlankCurrent
    private String operateTime;

}