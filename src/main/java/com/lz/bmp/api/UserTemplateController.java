package com.lz.bmp.api;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.entity.userTemplate.UserTemplateCommonAttr;
import com.lz.bmp.param.UserTemplateAddParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @Author shangang_luo
 * @Date 2021/1/7 15:52
 */

@RestController
@RequestMapping("/api/v1/usersTemplate")
public class UserTemplateController {

    @PostMapping("add")
    public Object addSourceTemplate(@RequestBody UserTemplateAddParam addParam) {
        BaseResult baseResult = new BaseResult();
        //模板自带属性参数检测
        UserTemplateCommonAttr sourceTemplateCommonAttr = addParam.getSourceTemplateCommonAttr();

        if (null == sourceTemplateCommonAttr) {
            return baseResult.setError(PollutionSourceErrorCode.PARAM_DATA_NULL_ERROR);
        }

        String sourceTemplateName = sourceTemplateCommonAttr.getSourceTemplateName();
        if (StringUtils.isEmpty(sourceTemplateName)) {
            return baseResult.setError(PollutionSourceErrorCode.PARAM_DATA_NULL_ERROR);
        }

        String uuid;
        if (StringUtils.isEmpty(addParam.getSourceTemplateCommonAttr().getSourceTemplateCode())) {
            //用UUID来设置模板唯一标识 sourceTemplateCode（会覆盖参数里的sourceTemplateCode值）
            uuid = SourceUtils.getUuid();
            addParam.getSourceTemplateCommonAttr().setSourceTemplateCode(uuid);
        } else {
            uuid = addParam.getSourceTemplateCommonAttr().getSourceTemplateCode();
            if (uuid.length() != Constants.NUMBER_UUID) {
                return baseResult.setError(PollutionSourceErrorCode.CODE_LENGTH_ERROR);
            }
            //模板code做校验
            SourceTemplateQueryParam sourceTemplateQueryParam = new SourceTemplateQueryParam();
            sourceTemplateQueryParam.setSourceTemplateCode(uuid);
            //这里获取的模板数量是包括删除的，因为模板code要唯一。
            PlainResult<Long> plainResult = sourceTemplateService.selectCountsOfSourceTemplate(sourceTemplateQueryParam);
            if (!plainResult.isSuccess()) {
                return baseResult.setError(plainResult.getCode(), plainResult.getMessage());
            }
            long count = plainResult.getData();
            if (count == Constants.ONE_ITEM) {
                return baseResult.setError(PollutionSourceErrorCode.CODE_ALREADY_EXSIT_ERROR);
            }
            if (count > Constants.ONE_ITEM) {
                return baseResult.setError(PollutionSourceErrorCode.MULTIPLE_DATA_ERROR);
            }
        }
        Date createTime = new Date();
        addParam.setCreateDate(createTime);
        addParam.setModifyTime(createTime);
        return sourceTemplateService.addSourceTemplate(addParam);
    }
}
