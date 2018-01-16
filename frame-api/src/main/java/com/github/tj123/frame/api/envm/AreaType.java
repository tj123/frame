package com.github.tj123.frame.api.envm;

public enum AreaType {

    ROOT("RT", "系统", 0, 0),

    PROVINCIAL("PR", "省", 1, 2),

    CIVIC("C", "市", 2, 4),

    DISTRICT_COUNTY("DC", "区/县", 3, 6),

    TOWN_STREET("TS", "镇/街", 4, 9),

    COMMUNITY_VILLAGE("CV", "社区/村", 5, 15);

    AreaType(String key, String value, Integer level, Integer length) {
        this.key = key;
        this.value = value;
        this.level = level;
        this.length = length;
    }

    private final Integer level;

    private final String key;

    private final String value;

    private Integer length;

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getLevel() {
        return level;
    }


}
