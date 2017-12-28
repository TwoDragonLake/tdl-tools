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

/**
 * 数据字典vo.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/5/8 18:15
 */
public class DictionaryVo implements Serializable {

    private static final long serialVersionUID = -5530995491150505068L;
    /* 名称 */
    private String name;
    /* 编码 */
    private String code;
    /* 标识 */
    private String sn;
    /* 备注 */
    private String remark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    @Override
    public String toString() {
        return "DictionaryVo{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", sn='" + sn + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
