package com.github.tj123.frame.api.pojo.dto;

import com.github.tj123.common.Dto;
import com.github.tj123.common.convert.BlankCurrent;
import com.github.tj123.frame.api.pojo.po.SUserPo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SUserDto implements Dto<SUserPo>, Serializable {

    public static final long serialVersionUID = 1L;

    private String id;

    @NotBlank
    private String username;

    private String password;

    private String sex;

    private String qq;

    private String email;

    @NotBlank
    private String depId;

    private String tel;

    private String phone;

    @BlankCurrent
    private String lastLogin;

    @NotBlank
    private String name;

    private String idNo;

    private String comment;

    private String createById;

    private String updateById;

    @BlankCurrent
    private String createTime;

    private String updateTime;

}
