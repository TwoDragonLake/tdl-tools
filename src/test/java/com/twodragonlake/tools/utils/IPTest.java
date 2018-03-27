/*
 * Copyright (C) 2018 The TwoDragonLake Open Source Project
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

package com.twodragonlake.tools.utils;

import org.junit.Test;

import java.util.Arrays;

/**
 * Ip地址获取.
 *
 * @author : Jerry xu
 * @version : 1.0
 * @since : 2017/12/28 0:35
 */
public class IPTest {

    @Test
    public void Test() {
        System.out.println("getMacAddr:" + IPUtils.getMacAddr());
        System.out.println("getMacAddress:" + IPUtils.getMacAddress());
        System.out.println("getLocalIP:" + IPUtils.getLocalIP());
        System.out.println("getAllLocalHostIP:" + Arrays.toString(IPUtils.getAllLocalHostIP()));
    }
}
