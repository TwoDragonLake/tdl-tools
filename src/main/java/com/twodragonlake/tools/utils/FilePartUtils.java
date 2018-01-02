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

package com.twodragonlake.tools.utils;

import java.util.Calendar;

/**
 * 文件路径类.
 *
 * @author : Maxwell wen
 * @version :1.0
 * @since : 2016/1/17 14:15
 */
public class FilePartUtils {

    /**
     * 取得年月日
     *
     * @param module module
     * @return module+年/月/日/
     * @author Maxwell wen
     * @version : 1.0
     * @since : 2016/1/17 14:15
     */
    public static String getFilePath(String module) {
        StringBuilder path = new StringBuilder(module);
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int date = now.get(Calendar.DATE);
        path.append(year);
        path.append("/");
        path.append(month);
        path.append("/");
        path.append(date);
        path.append("/");
        return path.toString();
    }
}
