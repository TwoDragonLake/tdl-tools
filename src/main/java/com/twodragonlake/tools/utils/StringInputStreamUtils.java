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

import java.io.*;

/**
 * StringInputStreamUtils工具类.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/1/12 14:15
 */
public class StringInputStreamUtils {

    public static InputStream convertStringToStream2(String str,
                                                     String charsetName) throws UnsupportedEncodingException {
        return new ByteArrayInputStream(str.getBytes(charsetName));
    }

    public static InputStream convertStringToStream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    /**
     * 换行输出
     *
     * @param is InputStream
     * @return String
     * @author bruce.liu
     */
    public static String inputStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append("/n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 换行输出
     *
     * @param is InputStream
     * @return String
     * @author bruce.liu
     */
    public static String inputStreamToString1(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    /**
     * 原样输出
     *
     * @param in InputStream
     * @return String
     * @throws IOException 异常
     */
    public static String inputStreamToString2(InputStream in) throws IOException {
        StringBuilder out = new StringBuilder();
        byte[] b = new byte[4096];
        for (int n; (n = in.read(b)) != -1; ) {
            out.append(new String(b, 0, n));
        }
        return out.toString();
    }

    /**
     * 原样输出
     *
     * @param is InputStream
     * @return String
     * @throws IOException 异常
     * @author bruce.liu
     */
    public static String inputStreamToString3(InputStream is) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int i;
        while ((i = is.read()) != -1) {
            baos.write(i);
        }
        return baos.toString();
    }

}
