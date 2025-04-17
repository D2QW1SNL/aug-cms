package com.hl.aug.cms.response;

import java.io.Serializable;
import java.util.List;

public class PageData<T> implements Serializable {

    /**
     * 列表
     */
    private List<T> list;

    /**
     * 是否为最后一页
     */
    private boolean endPage;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public boolean isEndPage() {
        return endPage;
    }

    public void setEndPage(boolean endPage) {
        this.endPage = endPage;
    }
}
