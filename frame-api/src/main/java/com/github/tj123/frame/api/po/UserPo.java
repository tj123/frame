package com.github.tj123.frame.api.po;

import com.github.tj123.common.Po;
import com.github.tj123.frame.api.dto.UserDto;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by TJ on 2017/10/12.
 */
@Data
@Table(name = "s_user")
public class UserPo implements Po<UserDto>, Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String name;

    private String username;

    private String password;

    private String sex;

    private String qq;

    private String email;

    private String departmentId;

    private String tel;

    private String phone;

    private Date lastLogin;

    private String cardNo;

}
