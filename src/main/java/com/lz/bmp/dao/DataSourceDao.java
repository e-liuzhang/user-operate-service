package com.lz.bmp.dao;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.entity.datasource.DataSource;
import com.lz.bmp.param.dataSource.DataSourceDeleteParam;
import com.lz.bmp.param.dataSource.DataSourceQueryParam;
import com.lz.bmp.param.dataSource.DataSourceUpdateParam;


/**
 * @author rocky
 * @ClassName DataSourceDao
 * @Description  数据源访问接口
 * @Create by rocky
 * @Date 2020/10/19 上午11:10
 */
public interface DataSourceDao {

    /**
     * 增加数据源
     * @param dataSource
     */
    BaseResult addDataSource(DataSource dataSource);

    /**
     *
     * @param dataSource
     * @return
     */
    BaseResult checkDataSource(DataSource dataSource);


    /**
     * 删除数据源
     * @param dataSourceDeleteParam
     * @return
     */
    BaseResult deleteDataSource(DataSourceDeleteParam dataSourceDeleteParam);


    /**
     * 更数据源唯字段校验
     * @param dataSourceUpdateParam
     * @return
     */
    BaseResult checkDataSourceUpdateParam(DataSourceUpdateParam dataSourceUpdateParam);

    /**
     * 数据源的数量
     * @param dataSourceQueryParam
     * @return
     */
    PlainResult<Long> countDataSource(DataSourceQueryParam dataSourceQueryParam);

    /**
     *
     * @param dataSourceQueryParam
     * @return
     */
    ListResult<DataSource> selectPageOfDataSource(DataSourceQueryParam dataSourceQueryParam);

    /**
     * 根据名称查询数据源
     * @param dataSourceQueryParam
     * @return
     */
    ListResult<DataSource> findDatasource(DataSourceQueryParam dataSourceQueryParam);

    /**
     *
     * @param dataSourceUpdateParam
     * @return
     */
    PlainResult<Long> updateDataSource(DataSourceUpdateParam dataSourceUpdateParam);
}
