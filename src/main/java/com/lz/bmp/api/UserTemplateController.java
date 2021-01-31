package com.lz.bmp.api;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.dataenum.*;
import com.lz.bmp.dataenum.Number;
import com.lz.bmp.entity.userTemplate.UserTemplate;
import com.lz.bmp.entity.userTemplate.UserTemplateCommonAttr;
import com.lz.bmp.param.userTemplate.*;
import com.lz.bmp.service.UserTemplateService;
import com.lz.bmp.utils.CommonUtils;
import com.lz.bmp.utils.Constants;
import com.lz.bmp.vo.UserTemplateVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @Author shangang_luo
 * @Date 2021/1/7 15:52
 */

@RestController
@RequestMapping("/api/v1/userTemplate")
public class UserTemplateController {

    @Autowired
    private UserTemplateService userTemplateService;

    @PostMapping("add")
    public Object addUserTemplate(@RequestBody UserTemplateAddParam addParam) {
        BaseResult baseResult = new BaseResult();
        //模板自带属性参数检测
        UserTemplateCommonAttr userTemplateCommonAttr = addParam.getUserTemplateCommonAttr();

        if (null == userTemplateCommonAttr) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, UserTemplateField.USER_TEMPLATE_COMMONATTR.getField());
        }

        String userTemplateName = userTemplateCommonAttr.getUserTemplateName();
        if (StringUtils.isEmpty(userTemplateName)) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, UserTemplateField.USER_TEMPLATE_NAME.getField());
        }

        String uuid;
        if (StringUtils.isEmpty(userTemplateCommonAttr.getUserTemplateCode())) {
            //用UUID来设置模板唯一标识 sourceTemplateCode（会覆盖参数里的sourceTemplateCode值）
            uuid = CommonUtils.getUuid();
            addParam.getUserTemplateCommonAttr().setUserTemplateCode(uuid);
        } else {
            uuid = addParam.getUserTemplateCommonAttr().getUserTemplateCode();
            if (uuid.length() != Number.THIRTY_TWO.getNumber()) {
                return baseResult.setError(CommonErrorCode.LENGTH_ERROR, UserTemplateField.USER_TEMPLATE_CODE.getField(),
                        Number.THIRTY_TWO.getNumber());
            }
            //模板code做校验
            UserTemplateQueryParam userTemplateQueryParam = new UserTemplateQueryParam();
            userTemplateQueryParam.setUserTemplateCode(uuid);
            //这里获取的模板数量是包括删除的，因为模板code要唯一。
            PlainResult<Long> plainResult = userTemplateService.selectCountsOfUserTemplate(userTemplateQueryParam);
            if (!plainResult.isSuccess()) {
                return baseResult.setError(plainResult.getCode(), plainResult.getMessage());
            }
            long count = plainResult.getData();
            if (count >= Number.ONE.getNumber()) {
                return baseResult.setError(CommonErrorCode.HAS_EXIT, TableName.USER_TEMPLATE.getTableName(), count);
            }
        }
        Date createTime = new Date();
        addParam.setCreateTime(createTime);
        addParam.setModifyTime(createTime);
        return userTemplateService.addUserTemplate(addParam);
    }

    @PostMapping("delete")
    public Object deleteUserTemplate(@RequestBody UserTemplateDeleteParam deleteParam) {
        BaseResult baseResult = new BaseResult();
        if (null == deleteParam) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, Param.DELETEPARAM.getParam());
        }
        List<String> codeList = deleteParam.getUserTemplateCodeList();
        if (CollectionUtils.isEmpty(codeList)) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, Param.CODE_LIST.getParam());
        }

        return userTemplateService.deleteUserTemplate(deleteParam);
    }

    @PostMapping("getUserTemplateList")
    public Object getSourceTemplateList(@RequestBody UserTemplateQueryByCodeParam queryParam) {
        ListResult<UserTemplate> listResult = new ListResult<>();

        if (CollectionUtils.isEmpty(queryParam.getUserTemplateCodeList())) {
            return listResult.setError(CommonErrorCode.PARAM_ERROR, Param.CODE_LIST.getParam());
        }
        return userTemplateService.findUserTemplate(queryParam);
    }

    @PostMapping("queryPage")
    public Object queryPage(@RequestBody UserTemplateQueryPageParam queryParam) {
        ListResult<UserTemplateVo> listResult = new ListResult<>();

        if (null == queryParam.getPage()) {
            queryParam.setPage(Constants.PAGE_DEFAULT);
        }
        if (null == queryParam.getSize()) {
            queryParam.setSize(Constants.SIZE_DEFAULT);
        }

        //校验分页参数
        if (queryParam.getPage() <= 0 || queryParam.getSize() <= 0) {
            listResult.setError(CommonErrorCode.QUERY_PAGE_DETAIL_ERROR, queryParam.getPage(), queryParam.getSize());
            return listResult;
        }

        listResult = userTemplateService.querySourceTemplateByPage(queryParam);

        return listResult;
    }
}
