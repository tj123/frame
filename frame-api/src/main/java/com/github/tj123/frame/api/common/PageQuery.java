package com.github.tj123.frame.api.common;

import java.util.List;

@FunctionalInterface
public interface PageQuery<T> {

    List<T> query() throws Exception;

}
