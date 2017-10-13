package com.github.tj123.frame.api.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TJ on 2017/10/13.
 */
@Getter
@Setter
public class PageResponse<T> implements Serializable {

    /**
     * 列表内容
     */
    private List<T> rows;

    /**
     * 总长度
     */
    private Long total;

}
