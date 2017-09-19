package com.github.tj123.frame.service.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by TJ on 2017/9/19.
 */
@Component
@EnableScheduling
public class SystemHolder {

    @Scheduled(fixedRate = Long.MAX_VALUE)
    public void holder() {

    }

}
