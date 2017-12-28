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

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.Resource;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

import java.io.*;
import java.util.Properties;

/**
 * 加载资源文件.
 *
 * @author : Bruce liu
 * @version : 1.0
 * @since : 2014/3/24 11:25
 */
public class DecryptPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer {

    private Resource[] locations;
    private String fileEncoding;
    private String linuxSystemPath;
    private String windowsSystemPath;
    private Properties props;

    public void setFileEncoding(String fileEncoding) {
        this.fileEncoding = fileEncoding;
    }

    public void setLocations(Resource[] locations) {
        this.locations = locations;
    }

    public void setLinuxSystemPath(String linuxSystemPath) {
        this.linuxSystemPath = linuxSystemPath;
    }

    public void setWindowsSystemPath(String windowsSystemPath) {
        this.windowsSystemPath = windowsSystemPath;
    }

    public void loadProperties(Properties props) throws IOException {
        if (this.locations != null) {
            PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
            for (Resource location1 : this.locations) {
                InputStream is;
                File configDir = new File(linuxSystemPath);
                if (!configDir.exists()) {
                    is = this.getClass().getResourceAsStream(
                            windowsSystemPath + "/" + location1.getFilename());
                } else {
                    String filePath = linuxSystemPath + File.separator + location1.getFilename();
                    is = new FileInputStream(filePath);
                }
                try {
                    if (fileEncoding != null) {
                        propertiesPersister.load(props, new InputStreamReader(is, fileEncoding));
                    } else {
                        propertiesPersister.load(props, is);
                    }
                    this.props = props;
                } finally {
                    if (is != null)
                        is.close();
                }
            }
        }
    }

    public String getValue(String key) {
        return props.getProperty(key);
    }
}
