package com.github.tj123.frame.api.dto.sys;

import com.github.tj123.common.Dto;
import com.github.tj123.frame.api.po.sys.DepPo;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by TJ on 2017/10/12.
 */
@Data
public class DepDto implements Dto<DepPo>, Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;

    private String type;

    private String areaId;

    private String comment;

    private String createById;

    private String updateById;

    private String createTime;

    private String updateTime;

}
