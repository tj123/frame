package com.shundian.frame.common;

import com.github.pagehelper.PageHelper;
import com.shundian.lib.Page;
import com.shundian.lib.PageResult;

import java.util.List;

/**
 * Created by TJ on 2016/12/6.
 */
public class PageUtil<E> {

    private com.github.pagehelper.Page<Object> helperPage;
    private Page page;

    /**
     * 分页前调用
     * @param page
     * @return
     */
    public PageUtil<E> startPage(Page page){
        page.deft();
        helperPage = PageHelper.startPage(page.getPage(), page.getSize(), true);
        helperPage.setOrderBy(page.getSort());
        return this;
    }

    /**
     * 分页后调用
     * @param rows
     * @return
     */
    public PageResult<E> assembleResult(List<E> rows){
        PageResult<E> pageResult = new PageResult<E>();
        pageResult.setPage(helperPage);
        pageResult.setRows(rows);
        return pageResult;
    }

    /**
     * 获取分页插件的page对象
     * @return
     */
    public com.github.pagehelper.Page<Object> getHelperPage() {
        return helperPage;
    }
}
