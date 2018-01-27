package com.github.tj123.frame.api.common.utils;

import com.github.tj123.frame.api.common.exception.CannotConvertException;
import com.github.tj123.frame.api.envm.AreaType;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Pattern;

import static com.github.tj123.frame.api.envm.AreaType.*;

@Slf4j
public class AreaUtils {

    /**
     * 精简区域 id
     * <p>
     * 如 510100 变为 5101
     * <p>
     * 510000 变为 51
     * <p>
     * 510110 结果还是 510110
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
     */
    public static String deleteLatterZero(String code) {
        if (code == null)
            return "";
        return code.trim().replaceAll("0+$", "");
    }

    /**
     * 在后面添加 0 到指定长度
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

    /**
     * 根据编码获取类型
     */
    public static AreaType areaType(String code) throws CannotConvertException {
        return EnumUtils.toEnum(AreaType.class, simple(code).length(), "getLength");
    }

    /**
     * 是不是上下级关系
     */
    public static boolean isParent(String parentCode, String childCode) {
        if (!Pattern.compile("^" + simple(parentCode)).matcher(childCode).find()) {
            return false;
        }
        try {
            if (areaType(parentCode).getLevel() + 1 == areaType(childCode).getLevel()) {
                return true;
            }
        } catch (CannotConvertException e) {
            log.error(e.getMessage());
        }
        return false;

    }
}
