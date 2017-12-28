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

import com.twodragonlake.tools.vo.CommonTreeVo;
import com.twodragonlake.tools.vo.WrapperTreeVo;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * ModelTest.
 *
 * @author : Jerry xu
 * @version : 1.0
 * @since : 2017/12/28 0:51
 */
public class ModelTest {

    private static final Logger logger = Logger.getLogger(ModelTest.class);

    @Test
    public void Test() throws Exception {

        //list封装成tree---start
        List<CommonTreeVo> list = new ArrayList<CommonTreeVo>();

        CommonTreeVo root = new CommonTreeVo();
        root.setId("0");
        root.setName("root");
        list.add(root);

        CommonTreeVo root_1 = new CommonTreeVo();
        root_1.setId("1");
        root_1.setName("root-1");
        root_1.setPid("0");
        list.add(root_1);

        CommonTreeVo root_2 = new CommonTreeVo();
        root_2.setId("2");
        root_2.setName("root-2");
        root_2.setPid("0");
        list.add(root_2);

        CommonTreeVo root_1_1 = new CommonTreeVo();
        root_1_1.setId("11");
        root_1_1.setName("root-1-1");
        root_1_1.setPid("1");
        list.add(root_1_1);

        CommonTreeVo root_1_2 = new CommonTreeVo();
        root_1_2.setId("12");
        root_1_2.setName("root-1-2");
        root_1_2.setPid("1");
        list.add(root_1_2);

        CommonTreeVo root_1_3 = new CommonTreeVo();
        root_1_3.setId("13");
        root_1_3.setName("root-1-3");
        root_1_3.setPid("1");
        list.add(root_1_3);

        CommonTreeVo root_2_1 = new CommonTreeVo();
        root_2_1.setId("21");
        root_2_1.setName("root-2-1");
        root_2_1.setPid("2");
        list.add(root_2_1);

        WrapperTreeVo param = new WrapperTreeVo();
        param.setKey("id");
        param.setParentKey("pid");
        param.setChildrenKey("children");

        logger.info("封装树前：" + GsonUtils.toJson(list));
        logger.info("封装树后：" + GsonUtils.toJson(ModelUtils.wrapperTree(list, param)));
    }
}
