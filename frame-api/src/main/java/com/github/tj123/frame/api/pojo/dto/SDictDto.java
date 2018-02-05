package com.github.tj123.frame.api.pojo.dto;

import com.github.tj123.common.Dto;
import com.github.tj123.common.convert.BlankCurrent;
import com.github.tj123.frame.api.pojo.po.SDictPo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SDictDto implements Dto<SDictPo>, Serializable {

    public static final long serialVersionUID = 1L;

    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    private String order_;

    private String createById;

    @BlankCurrent
    private String createTime;

    private String updateById;

    private String updateTime;

    private String description;

}
