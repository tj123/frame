package com.github.tj123.frame.api.pojo.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Table(name = "s_dep_role")
public class SDepRolePo implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String depId;

    private String roleId;

}
