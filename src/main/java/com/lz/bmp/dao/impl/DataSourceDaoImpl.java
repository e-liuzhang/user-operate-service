package com.lz.bmp.dao.impl;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.dao.DataSourceDao;
import com.lz.bmp.dataenum.CommonErrorCode;
import com.lz.bmp.dataenum.DataSourceField;
import com.lz.bmp.dataenum.Number;
import com.lz.bmp.dataenum.TableName;
import com.lz.bmp.entity.datasource.DataSource;
import com.lz.bmp.param.dataSource.DataSourceDeleteParam;
import com.lz.bmp.param.dataSource.DataSourceQueryParam;
import com.lz.bmp.param.dataSource.DataSourceUpdateParam;
import com.lz.bmp.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author rocky
 * @ClassName DataSourceDaoImpl
 * @Description
 * @Create by rocky
 * @Date 2020/10/19 上午11:11
 */
@Repository
public class DataSourceDaoImpl implements DataSourceDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BaseResult addDataSource(DataSource dataSource) {
        BaseResult baseResult = new BaseResult();
        try {
            mongoTemplate.insert(dataSource, TableName.DATA_SOURCE.getTableName());
        } catch (Exception e) {
            return baseResult.setError(CommonErrorCode.ADD_ERROR, e.toString());
        }
        return baseResult;
    }

    @Override
    public BaseResult checkDataSource(DataSource dataSource) {
        BaseResult baseResult = new BaseResult();
        try {
            List<Criteria> criteriaList = new ArrayList<>();
            String displayName = dataSource.getDisplayName();
            String code = dataSource.getCode();
            Query query = new Query();
            Criteria criteria = new Criteria();
            Criteria criteriaName;
            if (!StringUtils.isEmpty(code)) {
                criteriaName = Criteria.where(DataSourceField.CODE.getFieldName()).is(code);
                criteriaList.add(criteriaName);
            }
            Criteria criteriaDisplayName;
            if (!StringUtils.isEmpty(displayName)) {
                criteriaDisplayName = Criteria.where(DataSourceField.DISPLAY_NAME.getFieldName()).is(displayName);
                criteriaList.add(criteriaDisplayName);
            }
            Criteria[] criteriaArray = new Criteria[criteriaList.size()];
            criteria.orOperator(criteriaList.toArray(criteriaArray));
            query.addCriteria(criteria);
            long count = mongoTemplate.count(query, DataSource.class, TableName.DATA_SOURCE.getTableName());
            if (count >= Number.ONE.getNumber()) {
                return baseResult.setError(CommonErrorCode.HAS_EXIT, "code和displayName需要全局唯一", count);
            }
            return baseResult;
        } catch (Exception e) {
            return baseResult.setError(CommonErrorCode.QUERY_ERROR, e.toString());
        }
    }

    @Override
    public BaseResult deleteDataSource(DataSourceDeleteParam dataSourceDeleteParam) {
        BaseResult baseResult = new BaseResult();
        try {
            List<String> ids = dataSourceDeleteParam.getIds();
            Query query = new Query();
            Criteria criteria = Criteria.where(DataSourceField.ID.getFieldName()).in(ids);
            query.addCriteria(criteria);
            Update update = new Update();
            update.set(DataSourceField.IS_DELETED.getFieldName(), Boolean.TRUE);
            int res = (int) mongoTemplate.updateFirst(query, update, TableName.DATA_SOURCE.getTableName()).getModifiedCount();

            if (res <= Number.ONE.getNumber()) {
                return baseResult.setError(CommonErrorCode.UPDATE_ERROR, res);
            }

            return baseResult;
        } catch (Exception e) {
            return baseResult.setError(CommonErrorCode.DELETE_ERROR, e.toString());
        }
    }

    @Override
    public BaseResult checkDataSourceUpdateParam(DataSourceUpdateParam dataSourceUpdateParam) {
        BaseResult baseResult = new BaseResult();
        Query query = new Query();
        String code = dataSourceUpdateParam.getCode();
        String displayName = dataSourceUpdateParam.getDisplayName();
        String id = dataSourceUpdateParam.getId();
        List<Criteria> criteriaList = new ArrayList<>();
        Criteria criteriaName;
        if (!StringUtils.isEmpty(code)) {
            criteriaName = Criteria.where(DataSourceField.CODE.getFieldName()).is(code);
            criteriaList.add(criteriaName);
        }
        Criteria criteriaDisplayName;
        if (!StringUtils.isEmpty(displayName)) {
            criteriaDisplayName = Criteria.where(DataSourceField.DISPLAY_NAME.getFieldName()).is(displayName);
            criteriaList.add(criteriaDisplayName);
        }
        Criteria criteria = new Criteria();
        Criteria[] criteriaArray = new Criteria[criteriaList.size()];
        criteria.orOperator(criteriaList.toArray(criteriaArray)).and(DataSourceField.ID.getFieldName()).is(id);
        query.addCriteria(criteria);
        long count = mongoTemplate.count(query, TableName.DATA_SOURCE.getTableName());
        if (count >= Number.ONE.getNumber()) {
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public PlainResult<Long> countDataSource(DataSourceQueryParam dataSourceQueryParam) {
        PlainResult<Long> plainResult = new PlainResult<>();
        try {
            Query query = new Query();
            Criteria criteria = new Criteria();
            String displayNameOrCode = dataSourceQueryParam.getDisplayNameOrCode();
            if (!StringUtils.isEmpty(displayNameOrCode)) {
                String dispalyNameOrCode = CommonUtils.regexPreHandle(displayNameOrCode);
                Criteria criteriaDispalyName = Criteria.where(DataSourceField.DISPLAY_NAME.getFieldName()).regex("^.*" + dispalyNameOrCode + ".*$");
                Criteria criteriaCode = Criteria.where(DataSourceField.CODE.getFieldName()).regex("^.*" + dispalyNameOrCode + ".*$");
                List<Criteria> criteriaList = new ArrayList<>();
                criteriaList.add(criteriaCode);
                criteriaList.add(criteriaDispalyName);
                Criteria[] arr = new Criteria[criteriaList.size()];
                criteriaList.toArray(arr);
                criteria.orOperator(arr);
            }
            criteria.and(DataSourceField.IS_DELETED.getFieldName()).is(Boolean.FALSE);
            query.addCriteria(criteria);
            long count = mongoTemplate.count(query, TableName.DATA_SOURCE.getTableName());
            plainResult.setData(count);
            return plainResult;
        } catch (Exception e) {
            return plainResult.setError(CommonErrorCode.QUERY_ERROR, e.toString());
        }
    }


    @Override
    public ListResult<DataSource> selectPageOfDataSource(DataSourceQueryParam dataSourceQueryParam) {
        ListResult<DataSource> listResult = new ListResult<>();
        try {
            Query query = new Query();
            String displayNameOrCode = dataSourceQueryParam.getDisplayNameOrCode();
            Criteria criteria = new Criteria();
            if (!StringUtils.isEmpty(displayNameOrCode)) {
                String dispalyNameOrCode = CommonUtils.regexPreHandle(displayNameOrCode);
                Criteria criteriaDispalyName = Criteria.where(DataSourceField.DISPLAY_NAME.getFieldName()).regex("^.*" + dispalyNameOrCode + ".*$");
                Criteria criteriaName = Criteria.where(DataSourceField.CODE.getFieldName()).regex("^.*" + dispalyNameOrCode + ".*$");
                List<Criteria> criteriaList = new ArrayList<>();
                criteriaList.add(criteriaName);
                criteriaList.add(criteriaDispalyName);
                Criteria[] arr = new Criteria[criteriaList.size()];
                criteriaList.toArray(arr);
                criteria.orOperator(arr);
            }
            criteria.and(DataSourceField.IS_DELETED.getFieldName()).is(Boolean.FALSE);
            query.addCriteria(criteria);
            Integer page = dataSourceQueryParam.getPage();
            Integer size = dataSourceQueryParam.getSize();
            int allPage = (page - 1) * size;
            // 分页查询
            query.skip(allPage).limit(size);
            //排序字段
            query.with(new Sort(Sort.Direction.ASC, DataSourceField.DISPLAY_NAME.getFieldName()));
            List<DataSource> dataSourceList = mongoTemplate.find(query, DataSource.class, TableName.DATA_SOURCE.getTableName());
            listResult.setData(dataSourceList);
            return listResult;
        } catch (Exception e) {
            return listResult.setError(CommonErrorCode.QUERY_ERROR, e.toString());
        }

    }

    @Override
    public ListResult<DataSource> findDatasource(DataSourceQueryParam dataSourceQueryParam) {
        ListResult<DataSource> listResult = new ListResult<>();
        try {
            String code = dataSourceQueryParam.getCode();
            Criteria criteria = Criteria.where(DataSourceField.CODE.getFieldName()).is(code);
            Query query = new Query();
            query.addCriteria(criteria);
            List<DataSource> dataSourceList = mongoTemplate.find(query, DataSource.class, TableName.DATA_SOURCE.getTableName());
            listResult.setData(dataSourceList);
            return listResult;
        } catch (Exception e) {
            return listResult.setError(CommonErrorCode.QUERY_ERROR, e.toString());
        }
    }

    @Override
    public PlainResult<Long> updateDataSource(DataSourceUpdateParam dataSourceUpdateParam) {
        PlainResult<Long> plainResult = new PlainResult<>();
        try {

            String id = dataSourceUpdateParam.getId();
            String accessMode = dataSourceUpdateParam.getAccessMode();
            String displayName = dataSourceUpdateParam.getDisplayName();
            String code = dataSourceUpdateParam.getCode();
            String parameter = dataSourceUpdateParam.getParameter();
            String returnType = dataSourceUpdateParam.getReturnType();
            String url = dataSourceUpdateParam.getUrl();
            Date modifyTime = dataSourceUpdateParam.getModifyTime();

            Query query = new Query();
            Criteria criteria = Criteria.where(DataSourceField.ID.getFieldName()).is(id);
            query.addCriteria(criteria);
            Update update = new Update();

            if (!StringUtils.isEmpty(displayName)) {
                update.set(DataSourceField.DISPLAY_NAME.getFieldName(), displayName);
            }

            if (!StringUtils.isEmpty(accessMode)) {
                update.set(DataSourceField.ACCESS_MODE.getFieldName(), accessMode);
            }

            if (!StringUtils.isEmpty(code)) {
                update.set(DataSourceField.CODE.getFieldName(), code);
            }

            if (!StringUtils.isEmpty(parameter)) {
                update.set(DataSourceField.PARAMETER.getFieldName(), parameter);
            }

            if (!StringUtils.isEmpty(returnType)) {
                update.set(DataSourceField.RETURN_TYPE.getFieldName(), returnType);
            }

            if (!StringUtils.isEmpty(url)) {
                update.set(DataSourceField.URL.getFieldName(), url);
            }

            if (null != modifyTime) {
                update.set(DataSourceField.MODIFY_TIME.getFieldName(), modifyTime);
            }
            long modifiedCount = mongoTemplate.updateFirst(query, update, TableName.DATA_SOURCE.getTableName()).getModifiedCount();
            plainResult.setData(modifiedCount);
            return plainResult;
        } catch (Exception e) {
            return plainResult.setError(CommonErrorCode.QUERY_ERROR, e.toString());
        }

    }


}
