package com.shundian.frame.api.po.sys;

import com.shundian.frame.api.dto.sys.DepartmentDto;
import com.shundian.frame.api.envm.DepartmentType;
import com.shundian.lib.common.bean.BasePo;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Setter
@Getter
@Table(name = "tsys_department")
public class DepartmentPo extends BasePo<DepartmentDto> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String parentId;

    private String code;

    private String name;

    private DepartmentType type;

    private Boolean status;

    private String address;

    private String areaId;

}