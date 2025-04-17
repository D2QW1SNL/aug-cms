package com.hl.aug.cms.response;

import java.util.List;

public class PaginatedResultData<T> {

    private int currentPage = 0;

    private int totalPage = 0;

    private int totalCount;

    private List<T> data;

    public PaginatedResultData() {

    }


    /**
     * Constructor Method
     *
     * @param currentPage
     * @param totalPage
     * @param data
     */
    public PaginatedResultData(int currentPage, int totalPage, List<T> data) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.data = data;
    }


    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
