package com.github.tj123.frame.api.pojo.dto;

import com.github.tj123.common.Dto;
import com.github.tj123.frame.api.pojo.po.SAreaPo;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

@Getter
@Setter
@ToString
public class SAreaDto implements Dto<SAreaPo>, Serializable {

    public static final long serialVersionUID = 1L;

    private String areaId;

    private String parentId;

    @NotBlank
    private String name;

    @NotBlank
    private String level_;

    @NotBlank
    private String order_;

    /**
     * YES:是
            NO:否
     */
    @NotBlank
    private String isCapital;

    /**
     * YES:是
            NO:否
     */
    @NotBlank
    private String isGov;

    private String mapPoint;

    private String fullName;

}
