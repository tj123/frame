package com.github.tj123.frame.web.common;

import com.github.tj123.common.auth.UpdateAuthorizeListener;
import com.github.tj123.common.auth.UpdateSessionListener;
import org.springframework.stereotype.Component;

/**
 * Created by TJ on 2017/9/19.
 */
@Component
public class UpdateSession implements
    UpdateSessionListener, UpdateAuthorizeListener {

    public void onUpdateAuthorization() {
        System.out.println("update auth");
    }

    public void onUpdateSession(String userId) {
        System.out.println("update session");
    }
}
