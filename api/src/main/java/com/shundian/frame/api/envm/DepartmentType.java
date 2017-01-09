package com.shundian.frame.api.envm;

import com.shundian.lib.envm.EnumType;

/**
 * Created by TJ on 2016/12/5.
 */
public enum DepartmentType implements EnumType {
    /**
     *Food and Drug Administration
     */
    PROVINCIAL_FDA("PFDA","省食药局"),
    CIVIC_FDA("CFDA","市食药局"),
    DISTRICT_COUNTY_FDA("DCFDA","区县食药局"),
    TOWN_STREET_FDA("TSFDA","镇街食药所"),
    
    /**
     * Education Bureau
     */
    PROVINCIAL_EB("PEB","省教育局"),
    CIVIC_EB("CEB","市教育局"),
    DISTRICT_COUNTY_EB("DCEB","区县教育局"),
    
    /**
     * public security bureau
     */
    PROVINCIAL_PSB("PPSB","省公安厅"),
    CIVIC_PSB("CPSB","市公安厅"),
    DISTRICT_COUNTY_PSB("DCPSB","区县公安局"),
    /**
     * local police station
     */
    TOWN_STREET_LPS("TSLPS","镇街派出所");

    private String key;

    private String value;

    DepartmentType(String key, String value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public String getValue() {
        return value;
    }
}
