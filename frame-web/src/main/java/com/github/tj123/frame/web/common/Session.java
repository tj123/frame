package com.github.tj123.frame.web.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

/**
 * Created by TJ on 2017/9/20.
 */
@Setter
@Getter
@Component
@SessionScope
public class Session {

    private String userId;

    private String name;

    private String depId;

}
