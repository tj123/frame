package com.github.tj123.frame.api.po.sys;

import com.github.tj123.common.Po;
import com.github.tj123.frame.api.dto.sys.DepDto;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Data
@Table(name = "s_dep")
public class DepPo implements Po<DepDto>, Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String name;

    private String type;

    private String areaId;

    private String comment;

    private String createById;

    private String updateById;

    private Date createTime;

    private Date updateTime;

}
