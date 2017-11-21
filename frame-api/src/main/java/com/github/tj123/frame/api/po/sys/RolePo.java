package com.github.tj123.frame.api.po.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by TJ on 2017/9/20.
 */
@Setter
@Getter
@ToString
@Table(name = "s_role")
public class RolePo implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String name;

    private String description;

    private String createById;

    private String updateById;

    private Date createTime;

    private Date updateTime;

}
