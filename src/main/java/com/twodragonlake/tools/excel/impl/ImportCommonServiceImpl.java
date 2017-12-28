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

package com.twodragonlake.tools.excel.impl;

import com.twodragonlake.tools.excel.IImportCommonService;
import com.twodragonlake.tools.excel.ImportExcelCallBack;
import com.twodragonlake.tools.vo.ImportParameterVo;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel.
 *
 * @author : Ceaser wang
 * @version : 1.0
 * @since : 2016/3/2 15:01
 */
@Service
public class ImportCommonServiceImpl implements IImportCommonService {

    @Override
    public <T> void importExcel(MultipartFile file, ImportParameterVo<T> param, ImportExcelCallBack callback, Object... customParams) throws Exception {
        if (file == null) {
            throw new Exception("file参数不能为空");
        }
        if (param == null) {
            throw new Exception("param参数不能为空");
        }
        if (param.getObj() == null) {
            throw new Exception("param.getObj()为空");
        }
        InputStream inputStream = file.getInputStream();
        // 1、得到所有excel的数据
        List<T> excelInfo = this.readReport(inputStream, param, callback, customParams);
        param.setList(excelInfo);
        if (callback != null) {
            callback.successCallBack(param, customParams);
        }
    }

    private <T> List<T> readReport(InputStream inp, ImportParameterVo<T> param, ImportExcelCallBack callback, Object... customParams) throws Exception {
        List<T> listTs = new ArrayList<T>();
        try {
            String cellStr = null;
            Workbook wb = WorkbookFactory.create(inp);
            Sheet sheet = wb.getSheetAt(0);// 取得第一个sheets 
            if (sheet.getLastRowNum() < 1) {
                throw new Exception("该excel文件没有可以导入的数据");
            }
            ImportParameterVo<T> tempParam = new ImportParameterVo<T>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {//从第二行开始读取数据
                T addT = (T) param.getObj().getClass().newInstance();
                Row row = sheet.getRow(i);// 获取行(row)对象
                if (row == null) {
                    continue;
                }
                for (int j = 0; j < row.getLastCellNum(); j++) {
                    try {
                        Cell cell = row.getCell(j); // 获得单元格(cell)对象
                        if (cell != null) {//防止Excel中某些栏位为空直接报空指针异常
                            cellStr = ConvertCellStr(cell, cellStr);// 转换接收的单元格
                        } else {
                            cellStr = "";
                        }
                        //去空格
                        if (StringUtils.isNotBlank(cellStr)) {
                            cellStr = cellStr.trim();
                        }
                        tempParam.setRow(i);
                        tempParam.setCol(j);
                        tempParam.setCellStr(cellStr);
                        tempParam.setObj(addT);
                        this.addT(tempParam, callback, customParams);//将单元格的数据添加至一个对象
                        cellStr = null;
                    } catch (Exception e) {
                        throw new Exception("导入报表时第" + (i + 1) + "行第" + (j + 1) + "列报错" + e.getMessage());
                    }
                }
                listTs.add(tempParam.getObj());
            }
        } finally {
            if (inp != null) {
                try {
                    inp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listTs;
    }

    /**
     * 读取excel转换类型
     *
     * @param cell
     * @param cellStr
     * @return
     * @author Ceaser wang
     * @version : 1.0
     * @since : 2016/3/2 15:01
     */
    private String ConvertCellStr(Cell cell, String cellStr) {
        DecimalFormat df = new DecimalFormat("0");
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                cellStr = cell.getStringCellValue();// 读取String
                break;
            case Cell.CELL_TYPE_BOOLEAN:
                cellStr = String.valueOf(cell.getBooleanCellValue());//得到Boolean对象的方法
                break;
            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {// 先看是否是日期格式
                    cellStr = cell.getDateCellValue().toString();//读取日期格式
                } else {
                    String strCell = String.valueOf(cell.getNumericCellValue());
                    if (strCell.indexOf(".") != -1) {
                        cellStr = String.valueOf(new BigDecimal(String.valueOf(cell.getNumericCellValue())));
                    } else {
                        cellStr = String.valueOf(df.format(cell.getNumericCellValue()));//读取数字
                    }
                }
                break;
            case Cell.CELL_TYPE_FORMULA:
                cellStr = String.valueOf(cell.getNumericCellValue());
                break;
        }
        return cellStr;
    }

    /**
     * 对各种数据进行验证
     *
     * @param param
     * @param callback
     * @param customParams
     * @param <T>
     * @throws Exception 异常
     * @author Ceaser wang
     * @version : 1.0
     * @since : 2016/3/2 15:01
     */
    private <T> void addT(ImportParameterVo<T> param, ImportExcelCallBack callback, Object... customParams) throws Exception {
        if (callback != null) {
            callback.validCallback(param, customParams);
        }
    }
}
