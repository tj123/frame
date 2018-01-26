package com.github.tj123.frame.api.pojo.dto;

import com.github.tj123.common.Dto;
import com.github.tj123.common.convert.BlankCurrent;
import com.github.tj123.frame.api.pojo.po.SDepPo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SDepDto implements Dto<SDepPo>, Serializable {

    public static final long serialVersionUID = 1L;

    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String type;

    @NotBlank
    private String areaId;

    /**
     * 这是要给注释哦os
     */
    private String comment;

    private String createById;

    private String updateById;

    @BlankCurrent
    private String createTime;

    @BlankCurrent
    private String updateTime;

}
