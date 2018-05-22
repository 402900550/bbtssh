package com.hfkj.bbt.base.page;

/**
 * Created by Administrator on 2017/6/14.
 */
public class PageInfo {

    /** 总页数 */
    private int totalPages;
    /** 总条数 */
    private int totalRows;
    /** 当前页 */
    private int currentPage = 1;
    /** 每页条数 */
    private int rowsOfPage = 10;


    /**
     * 构造方法
     * @param size
     */
    public PageInfo(int size) {}


    public PageInfo() {}

    /**
     * @param pageNum pageNum
     * @param pageLength pageLength
     */
    public PageInfo(String pageNum, String pageLength) {
        if (null != pageNum && pageNum.length() > 0) {
            this.currentPage = Integer.parseInt(pageNum);
        } else {
            this.currentPage = 1;
        }
        if (null != pageLength && pageLength.length() > 0) {
            this.rowsOfPage = Integer.parseInt(pageLength);
        }
    }

    /**
     * 获取totalPageCount
     * @return totalPages
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * 设置totalPageCount
     * @param totalPages 要设置的totalPageCount
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    /**
     * 获取totalRowCount
     * @return totalRows
     */
    public int getTotalRows() {
        return totalRows;
    }

    /**
     * 设置totalRowCount
     * @param totalRows 要设置的totalRowCount
     */
    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    /**
     * 获取currentPageNum
     * @return currentPage
     */
    public int getCurrentPage() {
        return currentPage <= 0 ? 1 : currentPage;
    }

    /**
     * 设置currentPageNum
     * @param currentPage 要设置的currentPageNum
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 获取rowsOfPage
     * @return rowsOfPage
     */
    public int getRowsOfPage() {
        return rowsOfPage <= 0 ? 10 : rowsOfPage;
    }

    /**
     * 设置rowsOfPage
     * @param rowsOfPage 要设置的rowsOfPage
     */
    public void setRowsOfPage(int rowsOfPage) {
        this.rowsOfPage = rowsOfPage;
    }

    /**
     * 功能描述: 添加说明
     * @return int int
     * Date: 2014-6-24下午02:07:07
     */
    public int getStartIndex() {
        return (getCurrentPage() - 1) * getRowsOfPage();
    }

}
