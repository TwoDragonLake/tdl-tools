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

import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

/**
 * ServletContentUtil工具类.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/1/12 14:15
 */
public class ServletContextUtils implements ServletContextAware {

    private static ServletContext servletContext;

    @SuppressWarnings("static-access")
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    /**
     * 得到ServletContext
     *
     * @return
     */
    public static ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * 得到servletContext中的对象
     *
     * @param name
     * @return
     */
    public static Object getAppObject(String name) {
        if (servletContext != null) {
            return servletContext.getAttribute(name);
        }
        return null;
    }
}
