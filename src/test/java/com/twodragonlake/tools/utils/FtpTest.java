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

import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Description.
 *
 * @author : Jerry xu
 * @version : 1.0
 * @since : 2017/12/27 23:47
 */
public class FtpTest {

    private static final Logger logger = Logger.getLogger(FtpTest.class);

    @Test
    public void Test() throws Exception {

        logger.info("获取文件地址：" + FilePartUtils.getFilePath("/prdct/imgs/"));

        FtpUtils packer = new FtpUtils("10.10.20.204", "wen", "wen1", 21);

        File file = new File("E:/test.txt");
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        packer.uploadFile(in, "test1.xml.bak", "/img/");

        /*File file2 = new File("D:/oschina-settings.xml");
        InputStream in2 = new FileInputStream(file2);
        packer.uploadFile(in2, "oschina-settings.xml", "/test/test/");

        File file3 = new File("d:/report.log");
        InputStream in3 = new FileInputStream(file3);
        packer.uploadFile(in3, "report.log", "/test/test/");*/
    }
}
