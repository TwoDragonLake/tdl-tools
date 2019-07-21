/*
 * Copyright (C) 2017 The TwoDragonLake Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.twodragonlake.tools.vo;

import com.twodragonlake.tools.pager.ORDERLY;

import java.io.Serializable;
import java.util.Map;

/**
 * 功能描述：查询参数封装
 */
public class QueryVo implements Serializable {

    private static final long serialVersionUID = 817880730448759944L;
    /* 页码 */
    private int pageNumber;
    /* 每页显示记录数 */
    private int pageSize = 15;
    /* UI排序 */
    private String orderBy;
    /* 排序 key是返回值的属性名 value是desc或者asc
     *  我们添加数据的时候最好用linkMap
     * */
    private Map<String, ORDERLY> sqlOrderBy;
    /* 页面索引 */
    private int pageIndex;
    /* 分页 */
    private String page;
    /* 行数 */
    private int rows;

    public QueryVo() {

    }

    public QueryVo(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageIndex() {
        if (this.pageIndex < 0) {
            this.pageIndex = 1;
        } else {
            this.pageIndex++;
        }
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Map<String, ORDERLY> getSqlOrderBy() {
        return sqlOrderBy;
    }

    public void setSqlOrderBy(Map<String, ORDERLY> sqlOrderBy) {
        this.sqlOrderBy = sqlOrderBy;
    }

}
