package com.github.tj123.frame.api.pojo.dto;

import com.github.tj123.common.DatePattern;
import com.github.tj123.common.Dto;
import com.github.tj123.frame.api.pojo.po.SDepPo;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@ToString
public class SDepDto implements Dto<SDepPo>, Serializable {

    public static final long serialVersionUID = 1L;

    private String id;

    @NotBlank
    private String name;

    private String type;

    private String areaId;

    /**
     * 这是要给注释哦os
     */
    private String comment;

    private String createById;

    private String updateById;

    @DatePattern("yyyy-MM-dd hh:mm:ss")
    private String createTime;

    @DatePattern("yyyy-MM-dd hh:mm:ss")
    private String updateTime;

}
