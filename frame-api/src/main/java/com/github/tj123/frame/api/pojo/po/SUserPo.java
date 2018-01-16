package com.github.tj123.frame.api.pojo.po;

import com.github.tj123.common.Po;
import com.github.tj123.frame.api.pojo.dto.SUserDto;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Table(name = "s_user")
public class SUserPo implements Po<SUserDto>, Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String username;

    private String password;

    private String sex;

    private String qq;

    private String email;

    private String departmentId;

    private String tel;

    private String phone;

    private Date lastLogin;

    private String name;

    private String cardNo;

    private String comment;

    private String createById;

    private String updateById;

    private Date createTime;

    private Date updateTime;

}
