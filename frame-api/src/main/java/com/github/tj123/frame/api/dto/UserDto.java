package com.github.tj123.frame.api.dto;

import com.github.tj123.common.Dto;
import com.github.tj123.common.convert.BlankCurrent;
import com.github.tj123.frame.api.po.UserPo;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by TJ on 2017/10/12.
 */
@Data
public class UserDto implements Dto<UserPo>, Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String sex;

    private String qq;

    private String email;

    private String departmentId;

    private String tel;

    private String phone;

    @BlankCurrent
    private String lastLogin;

    private String cardNo;

    private String createById;

    private String updateById;

    private String createTime;

    private String updateTime;

}
