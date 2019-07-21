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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * json工具.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/9/8 14:15
 */
public class FastJsonUtils {

    /**
     * 对象转成json
     * @param t 泛型
     * @param <T> 泛型
     * @return json字符串
     */
    public static <T> String objectToJson(T t) {
        return JSON.toJSONString(t, SerializerFeature.DisableCircularReferenceDetect);
        /*
         *  Fastjson的SerializerFeature序列化属性:
         *  QuoteFieldNames———-输出key时是否使用双引号,默认为true
         *	WriteMapNullValue——–是否输出值为null的字段,默认为false
         *	WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
         *	WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
         *	WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
         *	WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
         */
        //return JSON.toJSONString(t,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    public static <T> String objectToJson(T t, SerializerFeature... features) {
        return JSON.toJSONString(t, features);
        /*
         *  Fastjson的SerializerFeature序列化属性:
         *  QuoteFieldNames———-输出key时是否使用双引号,默认为true
         *	WriteMapNullValue——–是否输出值为null的字段,默认为false
         *	WriteNullNumberAsZero—-数值字段如果为null,输出为0,而非null
         *	WriteNullListAsEmpty—–List字段如果为null,输出为[],而非null
         *	WriteNullStringAsEmpty—字符类型字段如果为null,输出为”“,而非null
         *	WriteNullBooleanAsFalse–Boolean字段如果为null,输出为false,而非null
         */
        //return JSON.toJSONString(t,SerializerFeature.DisableCircularReferenceDetect,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
    }

    /**
     * json转成对象
     *
     * @param json json字符串
     * @param t    对象实体
     * @param <T>  泛型
     * @return 对象
     */
    public static <T> T jsonToObject(String json, Class<T> t) {
        return JSON.parseObject(json, t);
    }

}
