package com.lz.bmp.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.param.dataSource.DataSourceGetDataParam;
import com.lz.bmp.service.DataSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author liying22923
 * @version 1.0
 * @date 2020/12/10
 */
@Service
public class DataSourceServiceImpl implements DataSourceService {

    private static final Logger logger = LoggerFactory.getLogger(DataSourceServiceImpl.class);

    @Autowired
    private CheckFactory checkFactory;

    @Autowired
    private DataSourceDao dataSourceDao;

    @Autowired
    private AchieveFactory achieveFactory;

    @Override
    public PlainResult<Map<String, Object>> getData(DataSourceGetDataParam dataSourceGetData) {
        Map<String, Object>  map = new HashMap<>();
        PlainResult<Map<String, Object>> plainResult = new PlainResult<>();
        List<String> dataSourceCodes = dataSourceGetData.getDataSourceCodes();
        for (String code : dataSourceCodes) {
            DataSourceQueryParam dataSourceQueryParam = new DataSourceQueryParam();
            dataSourceQueryParam.setCode(code);
            ListResult<DataSource> datasourceResult = dataSourceDao.findDatasource(dataSourceQueryParam);
            if(!datasourceResult.isSuccess()) {
                return plainResult.setError(datasourceResult.getCode(),datasourceResult.getMessage());
            }
            List<DataSource> dataSourceList = datasourceResult.getData();
            int count = dataSourceList.size();
            if(count >= Constants.NUMBER_TWO) {
                return plainResult.setError(PollutionSourceErrorCode.DATA_SOURCE_MULTIPLE_DATA_ERROR, code);
            }
            if(count < Constants.NUMBER_ONE) {
                return plainResult.setError(PollutionSourceErrorCode.DATA_SOURCE_DATA_NOT_FOUND, code);
            }
            DataSource dataSource = dataSourceList.get(Constants.ONLY_ONE);
            String url = dataSource.getUrl();
            String parameter = dataSource.getParameter();
            String accessMode = dataSource.getAccessMode();
            String returnType = dataSource.getReturnType();
            Achieve achieve = achieveFactory.getAchieve(returnType);
            if(null == achieve) {
                return plainResult.setError(PollutionSourceErrorCode.DATA_SOURCE_CATEGORY_IS_ILLEGAL, code);
            }
            PlainResult<JSONObject> jsonObjectPlainResult = SourceUtils.getJSONObject(parameter);
            if(!jsonObjectPlainResult.isSuccess()){
                return jsonObjectPlainResult.setError(PollutionSourceErrorCode.DATA_SOURCE_DATA_PARAM_PARSER_ERROR, code);
            }

            try {
                JSONObject data = jsonObjectPlainResult.getData();
                Object o = achieve.achieveData(url, parameter, data, accessMode);
                map.put(code,o);
            } catch (Exception e) {
                plainResult.setError(PollutionSourceErrorCode.DATA_SOURCE_ACHIEVE_DATA_FAILED, code);
                return plainResult;
            }

        }
        plainResult.setData(map);
        return plainResult;
    }


    @Override
    public BaseResult addDataSource(DataSourceAddParam dataSourceAddParam) {
        BaseResult baseResult;
        String displayName = dataSourceAddParam.getDisplayName();
        String code = dataSourceAddParam.getCode();
        String url = dataSourceAddParam.getUrl();
        String parameter = dataSourceAddParam.getParameter();
        //生成默认的路参数
        String accessMode = dataSourceAddParam.getAccessMode();
        String returnType = dataSourceAddParam.getReturnType();
        DataSource dataSource = new DataSource();
        dataSource.setDisplayName(displayName);
        dataSource.setCode(code);
        baseResult = dataSourceDao.checkDataSource(dataSource);
        if(!baseResult.isSuccess()) {
            return baseResult;
        }
        //校验其是否能用
        Check check = checkFactory.getCheck(returnType);
        if(null == check) {
            return baseResult.setError(PollutionSourceErrorCode.DATASOURCE_IS_NOT_ASSIGN, returnType);
        }
        baseResult = check.checkRequest(url, parameter, accessMode);
        if(!baseResult.isSuccess()) {
            return  baseResult;
        }
        dataSource = new DataSource();
        BeanUtils.copyProperties(dataSourceAddParam, dataSource);
        dataSource.setCreateTime(new Date());
        dataSource.setModifyTime(new Date());
        dataSource.setDeleted(Boolean.FALSE);
        return  dataSourceDao.addDataSource(dataSource);
    }

    @Override
    public BaseResult deleteDataSource(DataSourceDeleteParam dataSourceDeleteParam) {
        BaseResult baseResult = dataSourceDao.deleteDataSource(dataSourceDeleteParam);
        if(!baseResult.isSuccess()) {
            return  baseResult.setError(PollutionSourceErrorCode.PARAM_DATA_NULL_ERROR);
        }
        return baseResult;
    }

    @Override
    public BaseResult updateDataSource(DataSourceUpdateParam dataSourceUpdateParam) {
        BaseResult baseResult = dataSourceDao.checkDataSourceUpdateParam(dataSourceUpdateParam);
        if(baseResult.isSuccess()) {
            return  baseResult.setError(PollutionSourceErrorCode.DATA_ALREADY_EXIST_ERROR);
        }
        String url = dataSourceUpdateParam.getUrl();
        String parameter = dataSourceUpdateParam.getParameter();
        String accessMode = dataSourceUpdateParam.getAccessMode();
        String returnType = dataSourceUpdateParam.getReturnType();
        Check check = checkFactory.getCheck(returnType);
        if(null == check){
            return baseResult.setError(PollutionSourceErrorCode.DATASOURCE_IS_INVALID);
        }
        PlainResult<Object> plainResult = check.checkRequest(url, parameter, accessMode);
        if(!baseResult.isSuccess()){
            baseResult.setCode(plainResult.getCode());
            baseResult.setMessage(plainResult.getMessage());
            baseResult.setSuccess(false);
            return baseResult;
        }
        dataSourceUpdateParam.setModifyTime(new Date());
        PlainResult<Long> longPlainResult = dataSourceDao.updateDataSource(dataSourceUpdateParam);
        if(!longPlainResult.isSuccess()) {
            baseResult.setCode(longPlainResult.getCode());
            baseResult.setMessage(longPlainResult.getMessage());
            baseResult.setSuccess(false);
            return baseResult;
        }
        return baseResult;
    }

    @Override
    public PlainResult<Long> countDataSource(DataSourceQueryParam dataSourceQueryParam) {
        return dataSourceDao.countDataSource(dataSourceQueryParam);
    }

    @Override
    public ListResult<DataSource> selectPageOfDataSource(DataSourceQueryParam dataSourceQueryParam) {
        return dataSourceDao.selectPageOfDataSource(dataSourceQueryParam);
    }

    @Override
    public PlainResult<Object> checkDataSource(DataSource dataSource) {
        PlainResult<Object> plainResult = new PlainResult<>();
        String accessMode = dataSource.getAccessMode();
        String parameter = dataSource.getParameter();
        String url = dataSource.getUrl();
        String returnType = dataSource.getReturnType();
        Check check = checkFactory.getCheck(returnType);
        if (null == check) {
            return plainResult.setError(PollutionSourceErrorCode.DATASOURCE_CATEGORY_IS_ILLEGAL, "类型check为空");
        }
        return  check.checkRequest(url, parameter, accessMode);
    }



}
