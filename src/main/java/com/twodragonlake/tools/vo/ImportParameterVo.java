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

import java.io.Serializable;
import java.util.List;

public class ImportParameterVo<T> implements Serializable {

    private static final long serialVersionUID = 6973437058478229831L;

    /* 行数，第1行开始 */
    private Integer row;
    /* 列数，第0列开始 */
    private Integer col;
    /* 单元格内容 */
    private String cellStr;
    /* 接收excel一行数据的对象 */
    private T obj;
    /* 返回的excel数据 */
    private List<T> list;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public String getCellStr() {
        return cellStr;
    }

    public void setCellStr(String cellStr) {
        this.cellStr = cellStr;
    }

    public T getObj() {
        return obj;
    }

    public void setObj(T obj) {
        this.obj = obj;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
