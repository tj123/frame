package com.github.tj123.frame.service.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 系统的配置项
 */
@Getter
@Setter
@ToString
@ConfigurationProperties(prefix = "system")
public class SystemProperties {

    /**
     * 管理员的 userId
     */
    private List<String> adminIds;


    /**
     * 判断是否为系统管理员
     */
    public boolean isAdmin(String userId) {
        if (adminIds == null || userId == null)
            return false;
        for (String adminId : adminIds) {
            if (userId.equals(adminId))
                return true;
        }
        return false;
    }

}
