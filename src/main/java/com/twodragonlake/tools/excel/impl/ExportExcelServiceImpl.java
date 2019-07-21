/*
 * Copyright (C) 2018 The TwoDragonLake Open Source Project
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

package com.twodragonlake.tools.excel.impl;

import com.twodragonlake.tools.excel.IExportExcelCallBackService;
import com.twodragonlake.tools.excel.IExportExcelService;
import com.twodragonlake.tools.vo.ExportExcelCommonVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Excel.
 *
 * @author : Ceaser wang
 * @version : 1.0
 * @since : 2016/3/2 15:01
 */
@Service
public class ExportExcelServiceImpl implements IExportExcelService {

    /**
     * 导出 Excel
     *
     * @param exportExcelCallBackService exportExcelCallBackService
     * @param exportExcelCommonVo        exportExcelCommonVo
     * @param response                   response
     * @param paramOnSuccess             paramOnSuccess
     * @param objectT                    objectT
     * @param <T>                        T
     * @throws Exception Exception
     */
    @Override
    public <T> void exportExcel(IExportExcelCallBackService exportExcelCallBackService, ExportExcelCommonVo exportExcelCommonVo,
                                HttpServletResponse response, T paramOnSuccess, Object... objectT) throws Exception {
        if (null == exportExcelCommonVo) {
            throw new Exception("exportExcelCommonVo不能为空");
        }
        if (StringUtils.isEmpty(exportExcelCommonVo.getExcelFileName())) {
            throw new Exception("excelFileName不能为空");
        }
        if (MapUtils.isEmpty(exportExcelCommonVo.getMapExportData())) {
            throw new Exception("mapExportData不能为空");
        }
        if (CollectionUtils.isEmpty(exportExcelCommonVo.getCellTitles())) {
            throw new Exception("cellTitles不能为空");
        }
        if (null == response) {
            throw new Exception("response不能为空");
        }

        HSSFWorkbook wb = new HSSFWorkbook();

        int sheetIndex = 0;
        for (Map.Entry<String, List<T>> entry : ((Map<String, List<T>>) exportExcelCommonVo.getMapExportData()).entrySet()) {
            HSSFSheet sheet = wb.createSheet(entry.getKey());

            // 生成标题行
            if (CollectionUtils.isNotEmpty(exportExcelCommonVo.getCellTitles())) {
                HSSFRow row = sheet.createRow(exportExcelCommonVo.getTitleRowIndex());
                for (int i = 0; i < exportExcelCommonVo.getCellTitles().size(); i++) {
                    row.createCell(i).setCellValue((String) exportExcelCommonVo.getCellTitles().get(i));
                }
            }
            // 从标题行的下一行开始生成数据
            for (int i = 0; i < entry.getValue().size(); i++) {
                T cache = entry.getValue().get(i);
                HSSFRow row = sheet.createRow(i + 1 + exportExcelCommonVo.getTitleRowIndex());
                for (int j = 0; j < exportExcelCommonVo.getCellTitles().size(); j++) {
                    row.createCell(j).setCellValue(exportExcelCallBackService.getExcelCellDataCallBack(j, cache));
                }
            }
            //生成自定义的单元格
            exportExcelCallBackService.genSpacialCellCallBack(wb, sheetIndex, sheet, exportExcelCommonVo.getEndTime());
            sheetIndex++;
        }

        //输出到页面
        response.setHeader("Content-disposition", "attachment; filename=" + exportExcelCommonVo.getExcelFileName());
        ServletOutputStream outputStream = null;

        try {
            outputStream = response.getOutputStream();
            wb.write(outputStream);
        } catch (IOException e) {
            System.out.println("导出模板出错" + e);
            throw e;
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                }
                exportExcelCallBackService.successCallBack(paramOnSuccess);
            } catch (IOException e) {
                System.out.println("flush stream fails" + e);
                throw e;
            }
        }
    }

}

	