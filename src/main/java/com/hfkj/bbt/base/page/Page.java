package com.hfkj.bbt.base.page;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */
public class Page<T> {

    /**
     * 分页信息
     */
    private PageInfo pageInfo;

    /**
     * 数据集
     */
    private List<T> data;

    public Page(PageInfo pageInfo, List<T> data) {
        this.pageInfo = pageInfo;
        this.data = data;
    }

    public Page(){

    }

    /**
     *
     * 获取pageInfo
     * @return pageInfo
     */
    public PageInfo getPageInfo() {
        return pageInfo;
    }

    /**
     *
     * 设置pageInfo
     * @param pageInfo 要设置的pageInfo
     */
    public void setPageInfo(PageInfo pageInfo) {
        this.pageInfo = pageInfo;
    }

    /**
     *
     * 获取data
     * @return data
     */
    public List<T> getData() {
        return data;
    }

    /**
     *
     * 设置data
     * @param data 要设置的data
     */
    public void setData(List<T> data) {
        this.data = data;
    }

}
