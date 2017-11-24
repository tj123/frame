package com.github.tj123.frame.api.dto.sys;

import com.github.tj123.common.Dto;
import com.github.tj123.frame.api.po.sys.AreaPo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by TJ on 2017/10/12.
 */
@Setter
@Getter
@ToString
public class AreaDto implements Dto<AreaPo>, Serializable{

    private static final long serialVersionUID = 1L;

    private String areaId;

    private String parentId;

    private String name;

    private String level;

    private String order;

    private String isCapital;

    private String isGov;

    private String mapPoint;

    private String fullName;

}
