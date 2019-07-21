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
import org.springframework.web.multipart.MultipartFile;

/**
 * Excel.
 *
 * @author : Ceaser wang
 * @version : 1.0
 * @since : 2016/10/11 15:01
 */
public interface IImportCommonService {

    /**
     * 导入 Excel
     *
     * @param file         导入的excel文件
     * @param param        参数里面的obj属性必填，用来映射excel文件内容的实体类
     * @param callback     回调接口
     * @param customParams 用户自定义对象，用于程序扩展，怎样传入，回调的时候怎样传回，不做处理
     * @param <T>          T
     * @throws Exception Exception
     */
    <T> void importExcel(MultipartFile file, ImportParameterVo<T> param, ImportExcelCallBack callback, Object... customParams) throws Exception;
}
