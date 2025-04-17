/**
 * Copyright 2014-now amugua.com All right reserved. This software is the
 * confidential and proprietary information of amugua.com ("Confidential
 * Information"). You shall not disclose such Confidential Information and shall
 * use it only in accordance with the terms of the license agreement you entered
 * into with amugua.com.
 */
package com.hl.aug.cms.response;

import java.util.List;

public class ResultsData<T> {
    List<T> list;

    /**
     *
     */
    public ResultsData() {
        super();
    }

    /**
     * Getter method for property <tt>list</tt>.
     *
     * @return property value of list
     */
    public List<T> getList() {
        return list;
    }

    /**
     * Setter method for property <tt>list</tt>.
     *
     * @param list value to be assigned to property list
     */
    public void setList(List<T> list) {
        this.list = list;
    }


}
