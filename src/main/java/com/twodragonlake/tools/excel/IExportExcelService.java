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

import com.twodragonlake.tools.vo.ExportExcelCommonVo;

import javax.servlet.http.HttpServletResponse;

/**
 * Excel.
 *
 * @author : Ceaser wang
 * @version : 1.0
 * @since : 2016/10/11 15:01
 */
public interface IExportExcelService {

    /**
     * 导出Excel
     *
     * @param exportExcelCallBackService
     * @param exportExcelCommonVo
     * @param response
     * @param paramOnSuccess
     * @param object
     * @param <T>
     * @throws Exception 异常
     * @author Ceaser wang
     * @version : 1.0
     * @since : 2016/10/11 15:52
     */
    <T> void exportExcel(IExportExcelCallBackService exportExcelCallBackService, ExportExcelCommonVo exportExcelCommonVo, HttpServletResponse response, T paramOnSuccess, Object... object) throws Exception;

}

	