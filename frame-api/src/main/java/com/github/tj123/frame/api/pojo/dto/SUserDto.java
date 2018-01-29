package com.github.tj123.frame.api.pojo.dto;

import com.github.tj123.common.DatePattern;
import com.github.tj123.common.Dto;
import com.github.tj123.common.convert.BlankCurrent;
import com.github.tj123.frame.api.pojo.po.SUserPo;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@ToString
public class SUserDto implements Dto<SUserPo>, Serializable {

    public static final long serialVersionUID = 1L;

    private String id;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    private String sex;

    private String qq;

    private String email;

    private String depId;

    private String tel;

    private String phone;

    @BlankCurrent
    private String lastLogin;

    private String name;

    private String idNo;

    private String comment;

    private String createById;

    private String updateById;

    @DatePattern("yyyy-MM-dd hh:mm:ss")
    private String createTime;

    @DatePattern("yyyy-MM-dd hh:mm:ss")
    private String updateTime;

}
