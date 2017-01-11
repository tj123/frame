package com.shundian.frame.api.dto.sys;

import com.shundian.frame.api.po.sys.DepartmentPo;
import com.shundian.lib.common.bean.BaseDto;
import com.shundian.lib.common.bean.validate.Validate;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Validate
public class DepartmentDto extends BaseDto<DepartmentPo> {

    private String parentId;

    private String code;

    private String name;

    private String type;

    private String status;

    private String address;

    private String areaId;

    private Date operateTime;

}