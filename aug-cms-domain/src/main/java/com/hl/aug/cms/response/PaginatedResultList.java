package com.hl.aug.cms.response;

import java.util.List;


public class PaginatedResultList<T> {

    private int currentPage = 0;

    private int totalPage = 0;

    private int totalCount;

    private List<T> list;

    public PaginatedResultList() {

    }


    /**
     * Constructor Method
     *
     * @param currentPage
     * @param totalPage
     * @param list
     */
    public PaginatedResultList(int currentPage, int totalPage, List<T> list) {
        this.currentPage = currentPage;
        this.totalPage = totalPage;
        this.list = list;
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
