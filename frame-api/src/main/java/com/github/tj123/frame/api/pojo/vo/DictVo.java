package com.github.tj123.frame.api.pojo.vo;


import com.github.tj123.frame.api.pojo.dto.SDictDto;
import com.github.tj123.frame.api.pojo.po.SDictItemPo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.util.List;


@Setter
@Getter
@ToString
public class DictVo{


    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    private String order_;

    private String createById;

    private String createTime;

    private String updateById;

    private String updateTime;

    private String description;

    private List<SDictItemPo> items;

    public SDictDto getDto(){
        SDictDto dto = new SDictDto();
        dto.setId(id);
        dto.setName(name);
        dto.setCode(code);
        dto.setOrder_(order_);
        dto.setCreateById(createById);
        dto.setCreateTime(createTime);
        dto.setUpdateTime(updateTime);
        dto.setUpdateById(updateById);
        dto.setDescription(description);
        return dto;
    }

}
