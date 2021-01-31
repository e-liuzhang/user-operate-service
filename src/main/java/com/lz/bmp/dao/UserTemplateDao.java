package com.lz.bmp.dao;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.entity.userTemplate.UserTemplate;
import com.lz.bmp.param.userTemplate.*;
import com.lz.bmp.vo.UserTemplateVo;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 14:26
 */

public interface UserTemplateDao {


    /**
     * 查询数量
     *
     * @param userTemplateQueryParam
     * @return
     */
    PlainResult<Long> selectCountsOfUserTemplate(UserTemplateQueryParam userTemplateQueryParam);

    /**
     * 添加模板
     *
     * @param addParam
     * @return
     */
    BaseResult addUserTemplate(UserTemplateAddParam addParam);

    /**
     * 删除模板
     *
     * @param deleteParam
     * @return
     */
    BaseResult deleteUserTemplate(UserTemplateDeleteParam deleteParam);

    /**
     * 查询模板
     *
     * @param param
     * @return
     */
    ListResult<UserTemplate> findUserTemplate(UserTemplateQueryByCodeParam param);

    /**
     * 分页查询模板
     *
     * @param queryParam
     * @return
     */
    ListResult<UserTemplateVo> querySourceTemplateByPage(UserTemplateQueryPageParam queryParam);
}
