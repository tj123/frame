package com.github.tj123.frame.api.common;

import java.util.List;

/**
 * Created by TJ on 2017/10/13.
 */
@FunctionalInterface
public interface PageQuery<T> {

    List<T> query() throws Exception;

}
