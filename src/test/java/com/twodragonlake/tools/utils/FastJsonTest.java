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
import com.twodragonlake.tools.vo.DBSourceInfo;
import com.twodragonlake.tools.vo.PagerModelVo;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.text.ParseException;
import java.util.Date;

/**
 * FastJson测试用例.
 *
 * @author : Jerry xu
 * @version : 1.0
 * @since : 2017/12/27 23:39
 */
public class FastJsonTest {

    private static final Logger logger = Logger.getLogger(FastJsonTest.class);

    @Test
    public void Test() throws ParseException {

        DBSourceInfo db = new DBSourceInfo();
        db.setCreateTime(new Date());
        db.setDelFlag(null);
        logger.info(JSON.toJSONString(db, SerializerFeature.DisableCircularReferenceDetect));//输出{"createTime":"2016-09-23 16:12:43","delFlag":1}
        logger.info(JSON.toJSONString(db, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
        //输出{"createTime":"2016-09-23 16:12:43","creator":null,"dbName":null,"delFlag":1,"driverClass":null,"id":null,"ip":null,"isCommon":null,"password":null,"port":null,"updateTime":null,"updator":null,"userId":null,"userName":null}
        PagerModelVo<DBSourceInfo> pm = new PagerModelVo<DBSourceInfo>();
        logger.info(JSON.toJSONString(pm, SerializerFeature.DisableCircularReferenceDetect));
        logger.info(JSON.toJSONString(pm, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullStringAsEmpty));
    }
}
