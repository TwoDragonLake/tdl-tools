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

import org.apache.commons.codec.binary.Base64;

import java.io.UnsupportedEncodingException;

/**
 * 基本实体类.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/12/26 14:15
 */
public class Base64Utils {

    /**
     * 使用Base64加密算法加密字符串
     *
     * @param plainText 进行编码字符串
     * @return 加密字符串
     * @throws UnsupportedEncodingException 不支持的编码异常
     * @author Maxwell wen
     * @version : 1.0
     * @since : 2016/12/26 14:15
     */
    public static String encodeStr(String plainText) throws UnsupportedEncodingException {
        byte[] b = plainText.getBytes("UTF-8");
        Base64 base64 = new Base64();
        b = base64.encode(b);
        return new String(b);
    }

    /**
     * 使用Base64解密
     *
     * @param encodeStr 进行编码字符串
     * @return 加密字符串
     * @author Maxwell wen
     * @version : 1.0
     * @since : 2016/12/26 14:15
     */
    public static String decodeStr(String encodeStr) {
        byte[] b = encodeStr.getBytes();
        Base64 base64 = new Base64();
        b = base64.decode(b);
        return new String(b);
    }
}
