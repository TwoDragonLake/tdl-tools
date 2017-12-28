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

/**
 * 返回vo.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/1/19 17:46
 */
public class ReturnVo<T> implements Serializable {

    private static final long serialVersionUID = -5580228202640516960L;
    /* 响应编码 */
    private Integer code;
    /* 响应消息 */
    private String msg;
    /* 返回的vo */
    private T data;
    /* 返回的list */
    private List<T> datas;

    public ReturnVo() {
        super();
    }

    public ReturnVo(Integer code, String msg) {
        super();
        this.code = code;
        this.msg = msg;
    }

    public ReturnVo(Integer code, String msg, T data, List<T> datas) {
        super();
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.datas = datas;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }
}