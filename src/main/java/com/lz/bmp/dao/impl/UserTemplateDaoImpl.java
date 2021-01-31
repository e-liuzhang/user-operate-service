package com.lz.bmp.dao.impl;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.dao.UserTemplateDao;
import com.lz.bmp.dataenum.CommonErrorCode;
import com.lz.bmp.dataenum.TableName;
import com.lz.bmp.dataenum.UserTemplateField;
import com.lz.bmp.dataenum.UserTemplateStatus;
import com.lz.bmp.entity.userTemplate.UserTemplate;
import com.lz.bmp.param.userTemplate.*;
import com.lz.bmp.utils.CommonUtils;
import com.lz.bmp.vo.UserTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 14:27
 */

@Repository
public class UserTemplateDaoImpl implements UserTemplateDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public PlainResult<Long> selectCountsOfUserTemplate(UserTemplateQueryParam param) {
        //这里对模板状态不进行过滤，来控制模板的唯一性。
        PlainResult<Long> plainResult = new PlainResult<>();
        Query query = new Query();
        String userTemplateCode = param.getUserTemplateCode();
        String userTemplateName = param.getUserTemplateName();
        String indexCode = UserTemplateField.USER_TEMPLATE_COMMONATTR.getField() + CommonUtils.POINT +
                UserTemplateField.USER_TEMPLATE_CODE.getField();
        String indexSourceName = UserTemplateField.USER_TEMPLATE_COMMONATTR.getField() + CommonUtils.POINT +
                UserTemplateField.USER_TEMPLATE_NAME.getField();

        Criteria criteria = new Criteria();
        if (!StringUtils.isEmpty(userTemplateCode)) {
            criteria = Criteria.where(indexCode).is(userTemplateCode);
        }
        if (!StringUtils.isEmpty(userTemplateName)) {
            criteria = Criteria.where(indexSourceName).is(userTemplateName);
        }

        query.addCriteria(criteria);
        try {
            long count = mongoTemplate.count(query, TableName.USER_TEMPLATE.getTableName());
            plainResult.setData(count);
        } catch (Exception e) {
            return plainResult.setError(CommonErrorCode.QUERY_ERROR, e.toString());
        }

        return plainResult;
    }

    @Override
    public BaseResult addUserTemplate(UserTemplateAddParam addParam) {
        BaseResult baseResult = new BaseResult();
        try {
            mongoTemplate.insert(addParam, TableName.USER_TEMPLATE.getTableName());
        } catch (Exception e) {
            return baseResult.setError(CommonErrorCode.ADD_ERROR, e.toString());
        }
        return baseResult;
    }

    @Override
    public BaseResult deleteUserTemplate(UserTemplateDeleteParam deleteParam) {
        BaseResult baseResult = new BaseResult();
        List<String> userTemplateCodeList = deleteParam.getUserTemplateCodeList();

        String index = UserTemplateField.USER_TEMPLATE_COMMONATTR.getField() + CommonUtils.POINT +
                UserTemplateField.USER_TEMPLATE_CODE.getField();
        Query query = new Query();
        try {
            Criteria criteria = Criteria.where(index).in(userTemplateCodeList);
            query.addCriteria(criteria);
            Update update = new Update();
            update.set(UserTemplateField.TYPE.getField(), UserTemplateStatus.DELETE.getType());
            long count = mongoTemplate.updateMulti(query, update, TableName.USER_TEMPLATE.getTableName()).getModifiedCount();
        } catch (Exception e) {
            return baseResult.setError(CommonErrorCode.DELETE_ERROR, e.toString());
        }

        return baseResult;
    }

    @Override
    public ListResult<UserTemplate> findUserTemplate(UserTemplateQueryByCodeParam param) {
        ListResult<UserTemplate> listResult = new ListResult<>();
        try {
            Query query = new Query();
            List<String> userTemplateCodeList = param.getUserTemplateCodeList();
            String indexCode = UserTemplateField.USER_TEMPLATE_COMMONATTR.getField() + CommonUtils.POINT +
                    UserTemplateField.USER_TEMPLATE_CODE.getField();

            List<Integer> statusList = new ArrayList<>();
            statusList.add(UserTemplateStatus.SHELVE.getType());
            statusList.add(UserTemplateStatus.UN_SHELVE.getType());

            Criteria criteria = Criteria.where(indexCode).in(userTemplateCodeList)
                    .and(UserTemplateField.STATUS.getField()).in(statusList);
            query.addCriteria(criteria);
            List<UserTemplate> sourceTemplateList = mongoTemplate.find(query, UserTemplate.class, TableName.USER_TEMPLATE.getTableName());

            //保证顺序
            List<UserTemplate> orderUserTemplateList = new ArrayList<>();
            for (String userTemplateCode : userTemplateCodeList) {
                for (UserTemplate userTemplate : sourceTemplateList) {
                    if (userTemplate.getUserTemplateCommonAttr().getUserTemplateCode().equals(userTemplateCode) && !orderUserTemplateList.contains(userTemplate)) {
                        orderUserTemplateList.add(userTemplate);
                    }
                }
            }
            listResult.setData(orderUserTemplateList);
            listResult.setCount(orderUserTemplateList.size());
        } catch (Exception e) {
            listResult.setError(CommonErrorCode.QUERY_ERROR, e.toString());
        }
        return listResult;
    }

    @Override
    public ListResult<UserTemplateVo> querySourceTemplateByPage(UserTemplateQueryPageParam queryParam) {
        ListResult<UserTemplateVo> listResult = new ListResult<>();
        String searchName = CommonUtils.regexPreHandle(queryParam.getName());
        Query query = new Query();
        List<Integer> statusList = new ArrayList<>();
        statusList.add(UserTemplateStatus.SHELVE.getType());
        statusList.add(UserTemplateStatus.UN_SHELVE.getType());

        String indexUserName = UserTemplateField.USER_TEMPLATE_COMMONATTR.getField() + CommonUtils.POINT +
                UserTemplateField.USER_TEMPLATE_NAME.getField();
        if (!StringUtils.isEmpty(indexUserName)) {
            Criteria criteria = Criteria.where(indexUserName).regex("^.*" + searchName + ".*$")
                    .and(UserTemplateField.STATUS.getField()).in(statusList);
            query.addCriteria(criteria);
        }
        long totalCount = mongoTemplate.count(query, UserTemplate.class, TableName.USER_TEMPLATE.getTableName());
        listResult.setTotalCount(Math.toIntExact(totalCount));
        int page = queryParam.getPage();
        int size = queryParam.getSize();
        int allPage = (page - 1) * size;
        // 分页查询
        query.skip(allPage).limit(size);

        try {
            List<UserTemplate> list = mongoTemplate.find(query, UserTemplate.class, TableName.USER_TEMPLATE.getTableName());
            listResult.setCount(list.size());

            List<UserTemplateVo> voList = new ArrayList<>();
            for (UserTemplate userTemplate : list) {
                UserTemplateVo userTemplateVo = new UserTemplateVo();
                userTemplateVo.setUserTemplateCode(userTemplate.getUserTemplateCommonAttr().getUserTemplateCode());
                userTemplateVo.setUserTemplateName(userTemplate.getUserTemplateCommonAttr().getUserTemplateName());
                voList.add(userTemplateVo);
            }
            listResult.setData(voList);
        } catch (Exception e) {
            return listResult.setError(CommonErrorCode.QUERY_ERROR, e.toString());
        }
        return listResult;
    }


}
