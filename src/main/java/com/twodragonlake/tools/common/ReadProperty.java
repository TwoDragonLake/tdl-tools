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

package com.twodragonlake.tools.common;

/**
 * 读取配置文件 application.property.
 *
 * @author : Bruce liu
 * @version : 1.0
 * @since : 2014/6/4 15:21
 */
public class ReadProperty {

    public DecryptPropertyPlaceholderConfigurer dppc;

    public String getValue(String key) {
        return dppc.getValue(key);
    }

    public void setDppc(DecryptPropertyPlaceholderConfigurer dppc) {
        this.dppc = dppc;
    }

}
