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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.twodragonlake.tools.common.TimestampTypeAdapter;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 转化成List的方法.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/9/8 14:15
 */
public class GsonUtils {

    public static Map<String, Object> getMap(String jsonString) {
        JSONObject jsonObject;
        try {
            jsonObject = JSONObject.fromObject(jsonString);
            @SuppressWarnings("unchecked")
            Iterator<String> keyIter = jsonObject.keys();
            String key;
            Object value;
            Map<String, Object> valueMap = new HashMap<String, Object>();
            while (keyIter.hasNext()) {
                key = keyIter.next();
                value = jsonObject.get(key);
                valueMap.put(key, value);
            }
            return valueMap;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转为对象
     */
    @SuppressWarnings("unchecked")
    public static Object jsonToObj(String json, @SuppressWarnings("rawtypes") Class clazz) {
        return GsonUtils.getGson().fromJson(json, clazz);
    }

    public static Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd HH:mm:ss");
        gsonBuilder.registerTypeAdapter(Timestamp.class, new TimestampTypeAdapter());
        return gsonBuilder.create();
    }

    /**
     * 对象转发成json
     */
    public static String toJson(Object obj) {
        return getGson().toJson(obj);
    }

    /**
     * 【当使用gson-1.4的时候使用这个方法转成json】对象转成json，例如ReturnVo<PriUser> returnVo = new ReturnVo<PriUser>(OmsConstant.ERROR, "登入失败");...
     * Type typeToken = new TypeToken<ReturnVo<PriUser>>(){}.getType();
     * return GsonUtils.getGson().toJson(returnVo,typeToken);
     *
     * @param obj                                      Object
     * @param typeToken：如果转成json的对象包含了泛型，需要指定TypeToken
     */
    public static String toJson(Object obj, Type typeToken) {
        return getGson().toJson(obj, typeToken);
    }
}