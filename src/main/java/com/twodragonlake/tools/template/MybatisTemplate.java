/*
 * Copyright (C) 2018 The TwoDragonLake Open Source Project
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

package com.twodragonlake.tools.template;

import com.github.miemiedev.mybatis.paginator.domain.Order;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.twodragonlake.tools.pager.ORDERLY;
import com.twodragonlake.tools.vo.PagerModelVo;
import com.twodragonlake.tools.vo.QueryVo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Mybatis模板抽象类.
 *
 * @author : Maxwell wen
 * @version : 1.0
 * @since : 2015/1/12 14:21
 */
public abstract class MybatisTemplate {

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    public <T> T selectOne(String statement) {
        return this.sqlSessionTemplate.selectOne(statement);
    }

    public <T> T selectOne(String statement, Object parameter) {
        return this.sqlSessionTemplate.selectOne(statement, parameter);
    }

    public <K, V> Map<K, V> selectMap(String statement, String mapKey) {
        return this.sqlSessionTemplate.selectMap(statement, mapKey);
    }

    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey) {
        return this.sqlSessionTemplate.selectMap(statement, parameter, mapKey);
    }

    public <K, V> Map<K, V> selectMap(String statement, Object parameter, String mapKey, RowBounds rowBounds) {
        return this.sqlSessionTemplate.selectMap(statement, parameter, mapKey, rowBounds);
    }

    public <E> List<E> selectList(String statement) {
        return this.sqlSessionTemplate.selectList(statement);
    }

    public <E> List<E> selectList(String statement, Object parameter) {
        return this.sqlSessionTemplate.selectList(statement, parameter);
    }

    public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
        return this.sqlSessionTemplate.selectList(statement, parameter, rowBounds);
    }

    public void select(String statement, ResultHandler handler) {
        this.sqlSessionTemplate.select(statement, handler);
    }

    public void select(String statement, Object parameter, ResultHandler handler) {
        this.sqlSessionTemplate.select(statement, parameter, handler);
    }

    public void select(String statement, Object parameter, RowBounds rowBounds, ResultHandler handler) {
        this.sqlSessionTemplate.select(statement, parameter, rowBounds, handler);
    }

    public int insert(String statement) {
        return this.sqlSessionTemplate.insert(statement);
    }

    public int insert(String statement, Object parameter) {
        return this.sqlSessionTemplate.insert(statement, parameter);
    }

    public int update(String statement) {
        return this.sqlSessionTemplate.update(statement);
    }

    public int update(String statement, Object parameter) {
        return this.sqlSessionTemplate.update(statement, parameter);
    }

    public int delete(String statement) {
        return this.sqlSessionTemplate.delete(statement);
    }

    public int delete(String statement, Object parameter) {
        return this.sqlSessionTemplate.delete(statement, parameter);
    }

    /**
     * 排序查询list
     *
     * @param params    参数
     * @param statement sql
     * @param orderBy   排序的map key是列名，value是desc或者asc
     * @param <E>       E
     * @return List
     */
    @SuppressWarnings("unchecked")
    protected <E> List<E> selectList(String statement, Object params, Map<String, ORDERLY> orderBy) {
        PageBounds pageBounds = new PageBounds(PageBounds.NO_PAGE, PageBounds.NO_ROW_LIMIT);
        //不让其查询总记录数
        pageBounds.setContainsTotalCount(false);
        //添加排序
        setOrderInfo(orderBy, pageBounds);
        return (List<E>) sqlSessionTemplate.selectList(statement, params, pageBounds);
    }

    /**
     * 分页结果集
     *
     * @param statement 声明
     * @param params    参数
     * @param queryVo   查询实体
     * @param <E>       E
     * @return List
     */
    @SuppressWarnings("unchecked")
    protected <E> List<E> getPagerModelListByQuery(String statement, Object params, QueryVo queryVo) {
        PageBounds pageBounds = new PageBounds(queryVo.getPageNumber(), queryVo.getPageSize());
        //不让其查询总记录数
        pageBounds.setContainsTotalCount(false);
        //添加排序
        setOrderInfo(queryVo.getSqlOrderBy(), pageBounds);
        return (List<E>) sqlSessionTemplate.selectList(statement, params, pageBounds);
    }

    /**
     * 分页查询方法
     *
     * @param params  参数
     * @param queryVo 查询分页参数
     * @param dataSql 数据集sql
     * @param <T>     泛型
     * @return 实体
     */
    protected <T> PagerModelVo<T> getPagerModelByQuery(Object params, QueryVo queryVo, String dataSql) {
        PageBounds pageBounds = new PageBounds(queryVo.getPageNumber(), queryVo.getPageSize());
        //添加排序
        this.setOrderInfo(queryVo.getSqlOrderBy(), pageBounds);
        List<T> datas = sqlSessionTemplate.selectList(dataSql, params, pageBounds);
        PageList<T> pageList = (PageList<T>) datas;
        // 获得结果集条总数
        int count = pageList.getPaginator().getTotalCount();
        return new PagerModelVo<>(count, datas);
    }


    /**
     * 设置排序信息
     *
     * @param orderBy    orderBy
     * @param pageBounds pageBounds
     */
    private void setOrderInfo(Map<String, ORDERLY> orderBy, PageBounds pageBounds) {
        List<Order> orders;
        if (orderBy != null && orderBy.size() > 0) {
            orders = new ArrayList<>();
            for (Entry<String, ORDERLY> order : orderBy.entrySet()) {
                orders.add(Order.create(order.getKey(), order.getValue().toString()));
            }
            if (CollectionUtils.isNotEmpty(orders)) {
                pageBounds.setOrders(orders);
            }
        }
    }

}
