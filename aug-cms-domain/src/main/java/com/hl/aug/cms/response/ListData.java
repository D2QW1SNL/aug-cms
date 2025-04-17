package com.hl.aug.cms.response;

import java.io.Serializable;
import java.util.List;

public class ListData<T> implements Serializable {

    List<T> list;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
