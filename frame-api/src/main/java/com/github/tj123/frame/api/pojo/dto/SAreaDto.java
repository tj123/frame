package com.github.tj123.frame.api.pojo.dto;

import com.github.tj123.common.Dto;
import com.github.tj123.frame.api.pojo.po.SAreaPo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class SAreaDto implements Dto<SAreaPo>, Serializable {

    public static final long serialVersionUID = 1L;

    @NotBlank
    private String areaId;

    @NotBlank
    private String parentId;

    @NotBlank
    private String name;

    private String level_;

    private String order_;

    /**
     * YES:是
            NO:否
     */
    private String isCapital;

    /**
     * YES:是
            NO:否
     */
    private String isGov;

    private String mapPoint;

    private String fullName;

}
