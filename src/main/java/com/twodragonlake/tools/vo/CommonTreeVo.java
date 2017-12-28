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

import java.util.List;

/**
 * 公共TreeVo.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/1/17 14:15
 */
public class CommonTreeVo {

    private String id;
    private String name;
    private String pid;
    private List<CommonTreeVo> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public List<CommonTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<CommonTreeVo> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "CommonTreeVo{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", pid='" + pid + '\'' +
                ", children=" + children +
                '}';
    }
}