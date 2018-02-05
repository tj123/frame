package com.github.tj123.frame.api.pojo.po;

import com.github.tj123.common.Po;
import com.github.tj123.frame.api.pojo.dto.SDictDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@Table(name = "s_dict")
public class SDictPo implements Po<SDictDto>, Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String name;

    private String code;

    private Integer order_;

    private String createById;

    private Date createTime;

    private String updateById;

    private Date updateTime;

    private String description;

}
