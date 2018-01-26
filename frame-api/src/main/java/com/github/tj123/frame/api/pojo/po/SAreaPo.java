package com.github.tj123.frame.api.pojo.po;

import com.github.tj123.common.Po;
import com.github.tj123.frame.api.pojo.dto.SAreaDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Getter
@Setter
@ToString
@Table(name = "s_area")
public class SAreaPo implements Po<SAreaDto>, Serializable {

    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String areaId;

    private String parentId;

    private String name;

    private Integer level_;

    private Integer order_;

    /**
     * YES:是
            NO:否
     */
    private Integer isCapital;

    /**
     * YES:是
            NO:否
     */
    private Integer isGov;

    private String mapPoint;

    private String fullName;

}
