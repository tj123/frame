package com.shundian.frame.api.dto.sys;

import com.shundian.frame.api.envm.UserRoleTypeEnum;
import com.shundian.frame.api.po.sys.UserPo;
import com.shundian.lib.common.bean.BaseDto;
import com.shundian.lib.common.bean.validate.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Validate
public class UserDto extends BaseDto<UserPo> {

    @NotBlank
    @MinLength(3)
    @MaxLength(32)
    @ValidRegExp("^[a-zA-Z0-9_]+$")
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    @InEnum(UserRoleTypeEnum.class)
    private String userType;

    @Assert({"0","1"})
    private String status;

    @NotBlank
    private String departmentId;

    @MinLength(2)
    @NotBlank
    private String realName;

    @Phone
    private String mobile;

    @Email
    private String email;

    @QQ
    private String qq;
}