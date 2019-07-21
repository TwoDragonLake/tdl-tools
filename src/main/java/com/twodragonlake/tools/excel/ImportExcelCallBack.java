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

package com.twodragonlake.tools.excel;

import com.twodragonlake.tools.vo.ImportParameterVo;

/**
 * Excel.
 *
 * @author : Ceaser wang
 * @version : 1.0
 * @since : 2016/10/11 15:01
 */
public interface ImportExcelCallBack {

    /**
     * @param param        参数：row 代表第几行，从1开始
     *                     参数：col 代表第几列，从0开始
     *                     参数：cellStr代表单元格的内容
     *                     参数：obj是用来接收该单元格内容的对象
     *                     参数：list是读取excel内容的存储list
     * @param customParams 怎样传入，怎样传出，程序不做处理
     * @param <T>          T
     * @throws Exception 异常
     */
    <T> void validCallback(ImportParameterVo<T> param, Object... customParams) throws Exception;

    /**
     * @param param        参数：row 代表第几行，从1开始
     *                     参数：col 代表第几列，从0开始
     *                     参数：cellStr代表单元格的内容
     *                     参数：obj是用来接收该单元格内容的对象
     *                     参数：list是读取excel内容的存储list
     * @param customParams 怎样传入，怎样传出，程序不做处理
     * @param <T>          T
     * @throws Exception Exception
     */
    <T> void successCallBack(ImportParameterVo<T> param, Object... customParams) throws Exception;
}
