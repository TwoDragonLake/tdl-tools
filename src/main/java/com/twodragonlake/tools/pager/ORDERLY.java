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

package com.twodragonlake.tools.pager;

/**
 * 排序的enum.
 *
 * @author : Bruce liu
 * @version : 1.0
 * @since : 2015/1/24 15:21
 */
public enum ORDERLY {
    desc, asc;

    public ORDERLY reverse() {
        return (this == asc) ? desc : asc;
    }
}
