package com.github.tj123.frame.api.po.sys;

import com.github.tj123.common.Po;
import com.github.tj123.frame.api.dto.sys.AreaDto;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Table(name = "s_area")
public class AreaPo implements Po<AreaDto>, Serializable{

    private static final long serialVersionUID = 1L;

    private String areaId;

    private String parentId;

    private String name;

    @Column(name = "level_")
    private String level;

    @Column(name = "order_")
    private String order;

    private String isCapital;

    private String isGov;

    private String mapPoint;

    private String fullName;

}
