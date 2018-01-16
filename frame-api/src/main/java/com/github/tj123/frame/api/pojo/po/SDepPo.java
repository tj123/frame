package com.github.tj123.frame.api.pojo.po;

import com.github.tj123.common.Po;
import com.github.tj123.frame.api.pojo.dto.SDepDto;
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
@Table(name = "s_dep")
public class SDepPo implements Po<SDepDto>, Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String name;

    private String type;

    private String areaId;

    /**
     * 这是要给注释哦os
     */
    private String comment;

    private String createById;

    private String updateById;

    private Date createTime;

    private Date updateTime;

}
