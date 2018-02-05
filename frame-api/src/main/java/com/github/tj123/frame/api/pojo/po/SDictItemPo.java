package com.github.tj123.frame.api.pojo.po;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Table(name = "s_dict_item")
public class SDictItemPo implements Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String dictId;

    @Column(name = "key_")
    private String key;

    @Column(name = "value_")
    private String value;

    @Column(name = "group_")
    private String group;

}
