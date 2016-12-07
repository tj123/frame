package com.shundian.frame.api.dto.sys;

import com.shundian.frame.api.po.sys.Role;
import com.shundian.lib.common.bean.BaseDto;
import com.shundian.lib.common.bean.BlankCurrent;
import com.shundian.lib.common.bean.DatePattern;
import com.shundian.lib.common.bean.validate.NotBlank;
import com.shundian.lib.common.bean.validate.Validate;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Validate
public class RoleDto extends BaseDto<Role> {

    private String id;

    @NotBlank
    private String name;

    private String uiSref;

    @DatePattern
    @BlankCurrent
    private String operateTime;

}