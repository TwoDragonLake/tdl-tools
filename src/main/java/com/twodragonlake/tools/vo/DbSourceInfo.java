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

/**
 * 数据库连接类.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2016/8/28 14:15
 */
public class DbSourceInfo extends BaseModelVo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String id;
    /* 用户id */
    private String userId;
    /* 是否公用 1是0否 */
    private Integer isCommon;
    /* 数据源驱动类 */
    private String driverClass;
    /* IP地址 */
    private String ip;
    /* 端口 */
    private String port;
    /* 数据库名称 */
    private String dbName;
    /* 用户名 */
    private String userName;
    /* 密码 */
    private String password;
    /* 开始时间 */
    private String startTime;
    /* 结束时间 */
    private String endTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getIsCommon() {
        return isCommon;
    }

    public void setIsCommon(Integer isCommon) {
        this.isCommon = isCommon;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "DBSourceInfo{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", isCommon=" + isCommon +
                ", driverClass='" + driverClass + '\'' +
                ", ip='" + ip + '\'' +
                ", port='" + port + '\'' +
                ", dbName='" + dbName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
