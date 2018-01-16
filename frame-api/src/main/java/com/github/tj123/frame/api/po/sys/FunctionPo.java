package com.github.tj123.frame.api.po.sys;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@ToString
@Table(name = "s_func")
public class FunctionPo implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String uid;

    private String parentUid;

    @Column(name = "key_")
    private String key;

    private String name;

    private String fullName;

}
