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

import com.twodragonlake.tools.vo.DictionaryVo;
import com.twodragonlake.tools.vo.WrapperTreeVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 基本实体类.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/1/17 14:15
 */
public class ModelUtils {

    private static final Logger logger = Logger.getLogger(ModelUtils.class);

    /**
     * 通过枚举得到数据字典vo
     *
     * @param clas    枚举class
     * @param methods 枚举方法数组【默认为 getCode,getName,getRemark,getSn】
     * @return List
     * @throws NoSuchMethodException     异常
     * @throws SecurityException         异常
     * @throws IllegalAccessException    异常
     * @throws IllegalArgumentException  异常
     * @throws InvocationTargetException 异常
     */
    public static List<DictionaryVo> getDictsByEnum(Class<?> clas, String... methods)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method mt = clas.getMethod("values");
        Object[] objs = (Object[]) mt.invoke(clas);

        List<DictionaryVo> dicts = setDictionaryVoInfo(objs, methods);
        return CollectionUtils.isNotEmpty(dicts) ? dicts : null;
    }

    /**
     * 通过枚举得到Map
     *
     * @param clas    枚举class
     * @param methods 枚举方法数组 【默认为 getSn,getCode】
     * @return key:sn(标识);value:code
     * @throws NoSuchMethodException     异常
     * @throws SecurityException         异常
     * @throws IllegalAccessException    异常
     * @throws IllegalArgumentException  异常
     * @throws InvocationTargetException 异常
     */
    public static Map<String, Object> getMapByEnum(Class<?> clas, String... methods)
            throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        boolean methodsFlag = false;
        if (ArrayUtils.isNotEmpty(methods)) {
            methodsFlag = true;
        }

        Method mt = clas.getMethod("values");
        Object[] objs = (Object[]) mt.invoke(clas);
        Map<String, Object> map = getMapInfo(methodsFlag, Arrays.asList(objs), methods);
        return MapUtils.isNotEmpty(map) ? map : null;
    }

    /**
     * 通过List得到Map
     *
     * @param lists       List集合
     * @param methodNames 【methods.length==1:key:默认为 getSn,value:Object;methods.length!=1:key:默认为 getSn,value:默认为 getCode】
     * @return key:默认为 getSn,value:Object
     * @throws NoSuchMethodException     异常
     * @throws IllegalAccessException    异常
     * @throws InvocationTargetException 异常
     */
    public static Map<String, Object> getMapByList(List<?> lists, String... methodNames) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        boolean methodsFlag = false;
        if (ArrayUtils.isNotEmpty(methodNames)) {
            methodsFlag = true;
        }
        Map<String, Object> map = getMapInfo(methodsFlag, lists, methodNames);
        return MapUtils.isNotEmpty(map) ? map : null;
    }

    /**
     * 转成map
     *
     * @param methodsFlag boolean
     * @param objs        List
     * @param methodNames 【methods.length==1:key:默认为 getSn,value:Object;methods.length!=1:key:默认为 getSn,value:默认为 getCode】
     * @return key:默认为 getSn,value:Object
     * @throws NoSuchMethodException     异常
     * @throws IllegalAccessException    异常
     * @throws InvocationTargetException 异常
     */
    private static Map<String, Object> getMapInfo(boolean methodsFlag, List<?> objs, String... methodNames)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (CollectionUtils.isEmpty(objs)) {
            return null;
        }
        Map<String, Object> map = new TreeMap<String, Object>();
        for (Object obj : objs) {
            Class<?> cls = obj.getClass();
            if (null != methodNames && methodNames.length == 1) {
                Method getSnMtd = cls.getMethod(methodsFlag && StringUtils.isNotBlank(methodNames[0]) ? methodNames[0].trim() : "getSn");
                String sn = getSnMtd.invoke(obj).toString();
                if (StringUtils.isNotBlank(sn)) {
                    map.put(sn.trim(), obj);
                }
            } else {
                Method getSnMtd;
                if (methodNames != null) {
                    getSnMtd = cls.getMethod(methodsFlag && StringUtils.isNotBlank(methodNames[0]) ? methodNames[0].trim() : "getSn");
                    Method getCodeMtd = cls.getMethod(methodsFlag && StringUtils.isNotBlank(methodNames[1]) ? methodNames[1].trim() : "getCode");
                    String sn = getSnMtd.invoke(obj).toString();
                    Object code = getCodeMtd.invoke(obj);
                    if (StringUtils.isNotBlank(sn) && null != code) {
                        map.put(sn.trim(), code);
                    }
                }
            }
        }
        return map;
    }


    /**
     * 通过List得到Map
     *
     * @param lists      List集合
     * @param methodName 默认为 getSn
     * @param <T>        T
     * @return key:默认为 getSn,value:T
     * @throws NoSuchMethodException     异常
     * @throws IllegalAccessException    异常
     * @throws InvocationTargetException 异常
     */
    public static <T> Map<String, T> getMapByListOneMtd(List<T> lists, String methodName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        boolean methodsFlag = false;
        if (StringUtils.isNotBlank(methodName)) {
            methodsFlag = true;
        }
        Map<String, T> map = getMapInfoOneMtd(methodsFlag, lists, methodName);
        return MapUtils.isNotEmpty(map) ? map : null;
    }

    /**
     * 转成map
     *
     * @param methodsFlag boolean
     * @param objs        List
     * @param methodName  methodName
     * @param <T>         T
     * @return Map 默认为 getSn,value:T
     * @throws NoSuchMethodException     异常
     * @throws IllegalAccessException    异常
     * @throws InvocationTargetException 异常
     */
    private static <T> Map<String, T> getMapInfoOneMtd(boolean methodsFlag, List<T> objs, String methodName)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if (CollectionUtils.isEmpty(objs)) {
            return null;
        }
        Map<String, T> map = new TreeMap<String, T>();
        for (T obj : objs) {
            Class<?> cls = obj.getClass();
            Method getSnMtd = cls.getMethod(methodsFlag ? methodName.trim() : "getSn");
            String sn = getSnMtd.invoke(obj).toString();
            if (StringUtils.isNotBlank(sn)) {
                map.put(sn.trim(), obj);
            }
        }
        return map;
    }

    /**
     * 将list转成数据字典vo
     *
     * @param dicts   List
     * @param methods 枚举方法数组【默认为 getCode,getName,getRemark】
     * @return List
     * @throws NoSuchMethodException     异常
     * @throws IllegalAccessException    异常
     * @throws InvocationTargetException 异常
     */
    public static List<DictionaryVo> convertDictionaryVo(List<?> dicts, String... methods) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        List<DictionaryVo> dictVos = null;
        if (CollectionUtils.isNotEmpty(dicts)) {
            dictVos = setDictionaryVoInfo(dicts.toArray(new Object[0]), methods);
        }
        return CollectionUtils.isNotEmpty(dictVos) ? dictVos : null;
    }

    /**
     * 设置数据字典vo
     *
     * @param objs    Object
     * @param methods 方法名称数组
     * @return List
     * @throws NoSuchMethodException     异常
     * @throws IllegalAccessException    异常
     * @throws InvocationTargetException 异常
     */
    private static List<DictionaryVo> setDictionaryVoInfo(Object[] objs, String... methods)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        boolean methodsFlag = false;
        if (ArrayUtils.isNotEmpty(methods)) {
            methodsFlag = true;
        }

        List<DictionaryVo> dicts = new ArrayList<DictionaryVo>();
        for (Object obj : objs) {
            Class<?> cls = obj.getClass();
            Method getCodeMtd = cls.getMethod(methodsFlag && StringUtils.isNotBlank(methods[0]) ? methods[0].trim() : "getCode");
            Method getNameMtd = cls.getMethod(methodsFlag && StringUtils.isNotBlank(methods[1]) ? methods[1].trim() : "getName");

            Method getRemarkMtd = null;
            try {
                getRemarkMtd = cls.getMethod(methodsFlag && StringUtils.isNotBlank(methods[2]) ? methods[2].trim() : "getRemark");
            } catch (Exception e) {
                e.printStackTrace();
            }

            Method getSnMtd = null;
            try {
                getSnMtd = cls.getMethod(methodsFlag && StringUtils.isNotBlank(methods[3]) ? methods[3].trim() : "getSn");
            } catch (Exception e) {
                e.printStackTrace();
            }

            String code = getCodeMtd.invoke(obj).toString();
            String name = getNameMtd.invoke(obj).toString();
            String remark = null != getRemarkMtd ? getRemarkMtd.invoke(obj).toString() : null;
            String sn = null != getSnMtd ? getSnMtd.invoke(obj).toString() : null;

            if (StringUtils.isNotBlank(code) && StringUtils.isNotBlank(name)) {
                DictionaryVo dict = new DictionaryVo();
                dict.setCode(code.trim());
                dict.setName(name.trim());
                dict.setRemark(StringUtils.isNotBlank(remark) ? remark.trim() : null);
                dict.setSn(StringUtils.isNotBlank(sn) ? sn.trim() : null);
                dicts.add(dict);
            }
        }

        return dicts;
    }

    /**
     * 通过枚举的类名取得枚举的值
     * 例如  ModelUtils.getEnumByClassName("com.xxx")
     * [{"sn":"re_handing","status":200,"name":"意向处理中"},
     * {"sn":"re_close","status":201,"name":"意向关闭"},
     * {"sn":"order_handing","status":202,"name":"订单处理中"},
     * {"sn":"order_close","status":203,"name":"订单关闭"},
     * {"sn":"contr_sign","status":204,"name":"合同签订"},
     * {"sn":"contr_cutout","status":205,"name":"合同终止"},
     * {"sn":"pro_wstart","status":206,"name":"项目待开工"},
     * {"sn":"pro_ing","status":207,"name":"项目施工中"},
     * {"sn":"pro_suspend","status":208,"name":"项目暂停"},
     * {"sn":"pro_cutout","status":209,"name":"项目终止"},
     * {"sn":"pro_finish","status":210,"name":"项目竣工"}]
     *
     * @param className className
     * @return List
     * @throws ClassNotFoundException    异常
     * @throws NoSuchMethodException     异常
     * @throws SecurityException         异常
     * @throws IllegalAccessException    异常
     * @throws IllegalArgumentException  异常
     * @throws InvocationTargetException 异常
     */
    public static List<Map<String, Object>> getEnumByClassName(String className)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Class<?> cls = Class.forName(className);
        Method valuesMTd = cls.getMethod("values");
        Object[] objs = (Object[]) valuesMTd.invoke(cls);
        List<Map<String, Object>> list = new ArrayList<>();
        for (Object obj : objs) {
            Map<String, Object> map = new HashMap<>();
            for (Method me : cls.getDeclaredMethods()) {
                String mtdName = me.getName();
                if (mtdName.startsWith("get")) {
                    try {
                        Method mtd = obj.getClass().getMethod(mtdName);
                        Object oj = mtd.invoke(obj);
                        map.put(StringUtils.uncapitalize(mtdName.substring(3)), oj);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            list.add(map);
        }
        return list;
    }

    /**
     * 将list封装树形结构
     *
     * @param list  List
     * @param param WrapperTreeVo
     * @param <T> T
     * @return List
     * @throws NoSuchFieldException     异常
     * @throws SecurityException        异常
     * @throws IllegalArgumentException 异常
     * @throws IllegalAccessException   异常
     */
    public static <T> List<T> wrapperTree(List<T> list, WrapperTreeVo param) throws NoSuchFieldException, SecurityException,
            IllegalArgumentException, IllegalAccessException {
        //long t1 = System.currentTimeMillis();
        DateTime begin = null;
        if (logger.isDebugEnabled()) {
            begin = new DateTime();
        }

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        String key = param.getKey();
        String parentKey = param.getParentKey();
        String childrenKey = param.getChildrenKey();
        if (StringUtils.isBlank(key) || StringUtils.isBlank(parentKey) || StringUtils.isBlank(childrenKey)) {
            return null;
        }

        Class<?> cls = list.get(0).getClass();
        Field fdKey = cls.getDeclaredField(key);
        fdKey.setAccessible(true);

        Field fdParentKey = cls.getDeclaredField(parentKey);
        fdParentKey.setAccessible(true);

        Field fdChildrenKey = cls.getDeclaredField(childrenKey);
        fdChildrenKey.setAccessible(true);

        Map<String, T> map = new HashMap<String, T>();
        for (T obj : list) {
            map.put((String) fdKey.get(obj), obj);
        }

        List<T> listTemp = new ArrayList<>();
        int i = 0;
        while (i < list.size()) {
            T obj = list.get(i);
            String parentTemp = (String) fdParentKey.get(obj);
            if (StringUtils.isNotBlank(parentTemp)) {
                T parentObj = map.get(parentTemp);
                @SuppressWarnings("unchecked")
                List<T> children = (List<T>) fdChildrenKey.get(parentObj);
                if (CollectionUtils.isEmpty(children)) {
                    children = new ArrayList<>();
                }
                children.add(obj);

                fdChildrenKey.set(parentObj, children);
            } else {
                listTemp.add(obj);
            }
            i++;
        }
        map = null;
		/*long t2 = System.currentTimeMillis();
		logger.info("ModelUtils-wrapperTree:耗时：" + (t2 - t1) + "ms");*/

        if (logger.isDebugEnabled()) {
            DateTime end = new DateTime();
            Duration drt = new Duration(begin, end);
            logger.debug("ModelUtils-wrapperTree:耗时d：" + drt.getMillis() + "ms");
        }
        return listTemp;
    }


}
