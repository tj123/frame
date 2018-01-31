package com.github.tj123.frame.web.common;

import com.github.tj123.frame.api.common.exception.CannotConvertException;
import com.github.tj123.frame.api.common.utils.EnumUtils;
import com.github.tj123.frame.api.envm.DepType;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.HashMap;
import java.util.Map;

@Setter
@Getter
@Slf4j
@Component
@SessionScope
public class Session {

    private String userId;

    private String username;

    private String name;

    private String depId;

    private String depName;

    private String parentAreaId;

    private String areaId;

    private String areaName;

    private DepType depType;

    public void fromMap(Map<String, Object> map) {
        setUsername((String) map.get("username"));
        setName((String) map.get("name"));
        setDepId((String) map.get("depId"));
        setDepName((String) map.get("depName"));
        setAreaId((String) map.get("areaId"));
        setAreaName((String) map.get("areaName"));
        setParentAreaId((String) map.get("parentAreaId"));
        setAreaId("510100");
        try {
            setDepType(EnumUtils.toEnum(DepType.class, (String) map.get("depType")));
        } catch (CannotConvertException e) {
            log.warn(e.getMessage());
        }
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("username", getUsername());
        map.put("name", getName());
        map.put("depName", getDepName());
        map.put("areaName", getAreaName());
        map.put("areaId", getAreaId());
        map.put("parentAreaId", getParentAreaId());
        return map;
    }

}
