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

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 字符串操作相关.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/4/2 14:15
 */
public class StringConvertUtils {

    /**
     * 将"1,2,3,4,5..."这种形式的字符串转成"'1','2','3','4'..."这种形式
     *
     * @param strs
     * @return
     * @Description:
     * @author wentaoxiang 2016年4月5日 下午5:03:36
     */
    public static String converString(String strs) {
        if (StringUtils.isNotBlank(strs)) {
            String[] idStrs = strs.trim().split(",");
            if (idStrs.length > 0) {
                StringBuilder sbf = new StringBuilder("");
                for (String str : idStrs) {
                    if (StringUtils.isNotBlank(str)) {
                        sbf.append("'").append(str.trim()).append("'").append(",");
                    }
                }
                if (sbf.length() > 0) {
                    sbf = sbf.deleteCharAt(sbf.length() - 1);
                    return sbf.toString();
                }
            }
        }
        return "";
    }

    /**
     * 将"1,2,3,4,5..."这种形式的字符串转成"1,2,3,4..."这种形式
     *
     * @param strs
     * @return
     * @Description:
     * @author wentaoxiang 2016年4月5日 下午5:03:36
     */
    public static String converStringTwo(String strs) {
        if (StringUtils.isNotBlank(strs)) {
            String[] idStrs = strs.trim().split(",");
            if (idStrs.length > 0) {
                StringBuilder sbf = new StringBuilder("");
                for (String str : idStrs) {
                    if (StringUtils.isNotBlank(str)) {
                        sbf.append(str.trim()).append(",");
                    }
                }
                if (sbf.length() > 0) {
                    sbf = sbf.deleteCharAt(sbf.length() - 1);
                    return sbf.toString();
                }
            }
        }
        return "";
    }

    /**
     * 将"1,2,3,4,5..."这种形式的字符串转成List<String> 集合
     *
     * @param strs
     * @return
     * @Description:
     * @author wentaoxiang 2016年4月5日 下午5:03:29
     */
    public static List<String> converStringToList(String strs) {
        if (StringUtils.isNotBlank(strs)) {
            String[] idStrs = strs.trim().split(",");
            if (idStrs.length > 0) {
                List<String> strsList = new ArrayList<String>();
                for (String str : idStrs) {
                    if (StringUtils.isNotBlank(str)) {
                        strsList.add(str.trim());
                    }
                }
                if (strsList.size() > 0) {
                    return strsList;
                }
            }
        }
        return null;
    }

    /**
     * 将"1,2,3,4,5..."这种形式的字符串转成List<Integer> 集合
     *
     * @param strs
     * @return
     * @Description:
     * @author wentaoxiang 2016年4月5日 下午5:03:29
     */
    public static List<Integer> converStringToIntegerList(String strs) {
        if (StringUtils.isNotBlank(strs)) {
            String[] idStrs = strs.trim().split(",");
            if (idStrs.length > 0) {
                List<Integer> strsList = new ArrayList<Integer>();
                for (String str : idStrs) {
                    if (StringUtils.isNotBlank(str)) {
                        strsList.add(Integer.parseInt(str.trim()));
                    }
                }
                if (strsList.size() > 0) {
                    return strsList;
                }
            }
        }
        return null;
    }

    /**
     * 将list 转换为 '1','2','3','4','5','6'
     *
     * @param strlist
     * @return
     * @Description:
     * @author wangzequan 2016 上午11:21:27
     */
    public static String convertListToString(List<String> strlist) {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtils.isNotEmpty(strlist)) {
            for (int i = 0; i < strlist.size(); i++) {
                if (i == 0) {
                    sb.append("'").append(strlist.get(i)).append("'");
                } else {
                    sb.append(",").append("'").append(strlist.get(i)).append("'");
                }
            }
        }
        return sb.toString();
    }

}
