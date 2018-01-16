package com.github.tj123.frame.service.common;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.tj123.frame.api.common.PageQuery;
import com.github.tj123.frame.api.common.PageRequest;
import com.github.tj123.frame.api.common.PageResponse;

import java.util.ArrayList;
import java.util.List;

public class PageUtils {

    public static <T> PageResponse<T> query(PageRequest request, PageQuery<T> query) throws Exception {
        Page<Object> page = PageHelper.startPage(request.getPage(), request.getSize());
        List<T> list = query.query();
        if (list == null) {
            list = new ArrayList<T>();
        }
        PageResponse<T> response = new PageResponse<T>();
        response.setRows(list);
        response.setTotal(page.getTotal());
        return response;
    }

}
