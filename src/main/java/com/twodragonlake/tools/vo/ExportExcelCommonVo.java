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

package com.twodragonlake.tools.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ExcelVo.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/3/2 14:15
 */
public class ExportExcelCommonVo<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /* 工作簿名称、数据的Map类 */
    private Map<String, List<T>> mapExportData;
    /* 文件名称 */
    private String excelFileName;
    /* 标题行的名称 */
    private List<String> cellTitles;
    /* 标题行的行号(第几行开始) */
    private int titleRowIndex;
    /* 截止查询日期 */
    private Date endTime;

    public Map<String, List<T>> getMapExportData() {
        return mapExportData;
    }

    public void setMapExportData(Map<String, List<T>> mapExportData) {
        this.mapExportData = mapExportData;
    }

    public String getExcelFileName() {
        return excelFileName;
    }

    public void setExcelFileName(String excelFileName) {
        this.excelFileName = excelFileName;
    }

    public List<String> getCellTitles() {
        return cellTitles;
    }

    public void setCellTitles(List<String> cellTitles) {
        this.cellTitles = cellTitles;
    }

    public int getTitleRowIndex() {
        return titleRowIndex;
    }

    public void setTitleRowIndex(int titleRowIndex) {
        this.titleRowIndex = titleRowIndex;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "ExportExcelCommonVo{" +
                "mapExportData=" + mapExportData +
                ", excelFileName='" + excelFileName + '\'' +
                ", cellTitles=" + cellTitles +
                ", titleRowIndex=" + titleRowIndex +
                ", endTime=" + endTime +
                '}';
    }
}
