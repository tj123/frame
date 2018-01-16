package com.github.tj123.frame.service.config;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class SystemHolder {

    @Scheduled(fixedRate = Long.MAX_VALUE)
    public void holder() {

    }

}
