package com.shundian.frame.api.po.sys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Table(name = "tsys_file")
public class FilePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    /**
     *  当前名称
     */
    private String currentName;

    /**
     * 原始名称
     */
    private String originName;

    private Date operateTime;

}