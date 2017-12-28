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

package com.twodragonlake.tools.common;

import java.net.InetAddress;

/**
 * UUID生成器.
 *
 * @author : Bruce liu
 * @version : 1.0
 * @since : 2012/3/4 15:21
 */
public class UUIDGenerator {

    public static String generate() {
        return format(getIP()) +
                format(getJVM()) + format(getHiTime()) +
                format(getLoTime()) + format(getCount());

    }

    private static final int IP;

    static {
        int ipadd;
        try {
            ipadd = toInt(InetAddress.getLocalHost().getAddress());
        } catch (Exception e) {
            ipadd = 0;
        }
        IP = ipadd;
    }

    private static short counter = (short) 0;

    private static final int JVM = (int) (System.currentTimeMillis() >>> 8);

    private static String format(int intVal) {
        String formatted = Integer.toHexString(intVal);
        StringBuilder buf = new StringBuilder("00000000");
        buf.replace(8 - formatted.length(), 8, formatted);
        return buf.toString();
    }

    private static String format(short shortVal) {
        String formatted = Integer.toHexString(shortVal);
        StringBuilder buf = new StringBuilder("0000");
        buf.replace(4 - formatted.length(), 4, formatted);
        return buf.toString();
    }

    private static int getJVM() {
        return JVM;
    }

    private static short getCount() {
        synchronized (UUIDGenerator.class) {
            if (counter < 0)
                counter = 0;
            return counter++;
        }
    }

    private static int getIP() {
        return IP;
    }

    private static short getHiTime() {
        return (short) (System.currentTimeMillis() >>> 32);
    }

    private static int getLoTime() {
        return (int) System.currentTimeMillis();
    }

    private static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = (result << 8) - Byte.MIN_VALUE + (int) bytes[i];
        }
        return result;
    }

}
