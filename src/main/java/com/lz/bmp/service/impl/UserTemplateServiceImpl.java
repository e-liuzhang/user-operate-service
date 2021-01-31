package com.lz.bmp.service.impl;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.dao.UserTemplateDao;
import com.lz.bmp.entity.userTemplate.UserTemplate;
import com.lz.bmp.param.userTemplate.*;
import com.lz.bmp.service.UserTemplateService;
import com.lz.bmp.vo.UserTemplateVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 14:24
 */

@Service
public class UserTemplateServiceImpl implements UserTemplateService {

    @Autowired
    private UserTemplateDao userTemplateDao;

    @Override
    public PlainResult<Long> selectCountsOfUserTemplate(UserTemplateQueryParam userTemplateQueryParam) {
        return userTemplateDao.selectCountsOfUserTemplate(userTemplateQueryParam);
    }

    @Override
    public BaseResult addUserTemplate(UserTemplateAddParam addParam) {
        return userTemplateDao.addUserTemplate(addParam);
    }

    @Override
    public BaseResult deleteUserTemplate(UserTemplateDeleteParam deleteParam) {
        return userTemplateDao.deleteUserTemplate(deleteParam);
    }

    @Override
    public ListResult<UserTemplate> findUserTemplate(UserTemplateQueryByCodeParam param) {
        return userTemplateDao.findUserTemplate(param);
    }

    @Override
    public ListResult<UserTemplateVo> querySourceTemplateByPage(UserTemplateQueryPageParam queryParam) {
        return userTemplateDao.querySourceTemplateByPage(queryParam);
    }
}
