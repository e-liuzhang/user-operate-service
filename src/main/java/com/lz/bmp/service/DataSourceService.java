package com.lz.bmp.service;


import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.entity.datasource.DataSource;
import com.lz.bmp.param.dataSource.*;

import java.util.Map;

/**
 * @author liying22923
 * @version 1.0
 * @date 2020/12/10
 */
public interface DataSourceService {
    /**
     * 获取数据源数据
     * @param dataSourceGetData
     * @return
     */
    PlainResult<Map<String, Object>> getData(DataSourceGetDataParam dataSourceGetData);
//
//
//    /**
//     * 增加数据源
//     * @param dataSourceAddParam
//     * @return
//     */
//    BaseResult addDataSource(DataSourceAddParam dataSourceAddParam);
//
//
//    /**
//     * 伪删除数据源
//     * @param dataSourceDeleteParam
//     * @return
//     */
//    BaseResult deleteDataSource(DataSourceDeleteParam dataSourceDeleteParam);
//
//    /**
//     * 更新数据源
//     * @param dataSourceUpdateParam
//     * @return
//     */
//    BaseResult updateDataSource(DataSourceUpdateParam dataSourceUpdateParam);
//
//
//    /**
//     * 计算DataSource
//     * @param dataSourceQueryParam
//     * @return
//     */
//    PlainResult<Long> countDataSource(DataSourceQueryParam dataSourceQueryParam);
//
//
//    /**
//     * 分页查询
//     * @param dataSourceQueryParam
//     * @return
//     */
//    ListResult<DataSource> selectPageOfDataSource(DataSourceQueryParam dataSourceQueryParam);
//
//
//    /**
//     * 校验传入的数据源是否和配置的符合
//     * @param dataSource
//     * @return
//     */
//    PlainResult<Object> checkDataSource(DataSource dataSource);
}
