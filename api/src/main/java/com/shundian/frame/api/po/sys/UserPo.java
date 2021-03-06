package com.shundian.frame.api.po.sys;

import com.shundian.frame.api.dto.sys.UserDto;
import com.shundian.frame.api.envm.UserRoleType;
import com.shundian.lib.common.bean.BasePo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Table(name = "tsys_user")
public class UserPo extends BasePo<UserDto> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(generator = "UUID")
	private String id;

	private String username;

	private String password;

	@Column(name = "type")
	private UserRoleType userType;

	private Boolean status;

	private String departmentId;

	private String realName;

	private String mobile;

	private String email;

	private String qq;

	private Date operateTime;

}
