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

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Date;

/**
 * Excel.
 *
 * @author : Ceaser wang
 * @version : 1.0
 * @since : 2016/3/2 15:01
 */
public interface IExportExcelCallBackService {

    /**
     * 生成特殊的单元格（合并）
     *
     * @param workbook   workbook
     * @param sheetIndex sheetIndex
     * @param sheet      sheet
     * @param endTime    endTime
     * @throws Exception 异常
     */
    void genSpacialCellCallBack(HSSFWorkbook workbook, int sheetIndex, HSSFSheet sheet, Date endTime) throws Exception;

    /**
     * 获取需要装载的Excel数据
     *
     * @param cellIndex       cellIndex
     * @param param           param
     * @param objectOnSuccess objectOnSuccess
     * @param <T>             T
     * @return String
     * @throws Exception Exception
     */
    <T> String getExcelCellDataCallBack(int cellIndex, T param, Object... objectOnSuccess) throws Exception;

    /**
     * 导出成功的回调方法
     *
     * @param paramOnSuccess        paramOnSuccess
     * @param customParamsOnSuccess customParamsOnSuccess
     * @param <T>                   T
     * @throws Exception Exception
     */
    <T> void successCallBack(T paramOnSuccess, Object... customParamsOnSuccess) throws Exception;

}