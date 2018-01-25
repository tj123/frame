package com.github.tj123.frame.service.common;

import static com.github.tj123.frame.api.envm.AreaType.*;

public class AreaUtil {

    /**
     * 精简区域 id
     * <p>
     * 如 510100 变为 5101
     * <p>
     * 510000 变为 51
     * <p>
     * 510110 结果还是 510110
     *
     * @param areaId
     * @return
     */
    public static String simple(String areaId) {
        if (areaId == null || areaId.trim().equals(""))
            return "";
        areaId = deleteLatterZero(areaId.trim());
        int length = areaId.length();
        return length == ROOT.getLength() ? "" :
            length <= PROVINCIAL.getLength() ? addZeroTo(areaId, PROVINCIAL.getLength()) :
                length <= CIVIC.getLength() ? addZeroTo(areaId, CIVIC.getLength()) :
                    length <= DISTRICT_COUNTY.getLength() ? addZeroTo(areaId, DISTRICT_COUNTY.getLength()) :
                        length <= TOWN_STREET.getLength() ? addZeroTo(areaId, TOWN_STREET.getLength()) :
                            addZeroTo(areaId, COMMUNITY_VILLAGE.getLength())
            ;
    }


    /**
     * 删除后面的 0
     *
     * @param code
     * @return
     */
    public static String deleteLatterZero(String code) {
        if (code == null)
            return "";
        return code.trim().replaceAll("0+$", "");
    }

    /**
     * 在后面添加 0 到指定长度
     *
     * @param code
     * @param length
     * @return
     */
    public static String addZeroTo(String code, int length) {
        if (code == null)
            return "";
        code = code.trim();
        int codeLen = code.length();
        if (codeLen >= length)
            return code;
        for (; codeLen < length; codeLen++) {
            code += "0";
        }
        return code;
    }

}
