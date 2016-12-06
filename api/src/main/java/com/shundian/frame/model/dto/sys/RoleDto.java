package com.shundian.frame.model.dto.sys;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Setter
@Getter
public class RoleDto {

    @Id
    @GeneratedValue(generator = "UUID")
    private String id;

    private String name;

    private String uiSref;

    private String operateTime;

}