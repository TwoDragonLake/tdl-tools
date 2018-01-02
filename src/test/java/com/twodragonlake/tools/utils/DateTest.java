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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.twodragonlake.tools.utils.DateUtils.addMonth;

/**
 * Description.
 *
 * @author : Jerry xu
 * @version : 1.0
 * @since : 2017/12/28 0:52
 */
public class DateTest {

    private static final Logger logger = Logger.getLogger(DateTest.class);

    @Test
    public void Test() throws Exception {
        logger.info(new SimpleDateFormat("yyyy-MM-dd mm:HH:ss").format(addMonth(new Date(), 2)));
        List<Date> days = DateUtils.getDatesBetweenTwoDate(DateUtils.getDate("2013-01-09"), DateUtils.getDate("2013-01-11"));
        logger.info(days.size());
    }
}
