package com.lz.bmp.api;

import com.alibaba.fastjson.JSONObject;
import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.dataenum.*;
import com.lz.bmp.dataenum.Number;
import com.lz.bmp.entity.user.User;
import com.lz.bmp.entity.user.UserCommonInfo;
import com.lz.bmp.entity.userTemplate.UserTemplate;
import com.lz.bmp.param.user.*;
import com.lz.bmp.param.userTemplate.UserTemplateQueryByCodeParam;
import com.lz.bmp.service.UserService;
import com.lz.bmp.service.UserTemplateCheckService;
import com.lz.bmp.service.UserTemplateService;
import com.lz.bmp.utils.CommonUtils;
import com.lz.bmp.vo.UserApiVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Author shangang_luo
 * @Date 2021/1/7 15:38
 */

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserTemplateService userTemplateService;

    @Autowired
    private UserService userService;


    @Autowired
    private UserTemplateCheckService userTemplateCheckService;


    @PostMapping("addUser")
    public Object addUser(@RequestBody UserAddForThreePartyParam addParam) {
        BaseResult baseResult = new BaseResult();
        UserCommonInfo userCommonInfo = addParam.getUserCommonInfo();
        if (null == userCommonInfo) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, UserField.USER_COMMON_INFO.getField());
        }
        String userTemplateCode = userCommonInfo.getUserTemplateCode();
        if (StringUtils.isEmpty(userTemplateCode)) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, UserField.USER_TEMPLATE_CODE.getField());
        }

        //校验模板的存在
        UserTemplateQueryByCodeParam queryParam = new UserTemplateQueryByCodeParam();
        List<String> userTemplateCodeList = new ArrayList<>();
        userTemplateCodeList.add(addParam.getUserCommonInfo().getUserTemplateCode());
        queryParam.setUserTemplateCodeList(userTemplateCodeList);
        ListResult<UserTemplate> userTemplateListResult = userTemplateService.findUserTemplate(queryParam);

        if (!userTemplateListResult.isSuccess()) {
            return baseResult.setError(userTemplateListResult.getCode(), userTemplateListResult.getMessage());
        }
        List<UserTemplate> userTemplateList = userTemplateListResult.getData();
        if (CollectionUtils.isEmpty(userTemplateList)) {
            return baseResult.setError(CommonErrorCode.NOT_EXIT, UserTemplateField.USER_TEMPLATE_CODE.getField(), userTemplateCode);
        }

        //校验添加的基本属性与模板规定的是否一致
        Map<String, Map<String, String>> userBasicInfoMap = addParam.getUserBasicInfoMap();
        if (null != userBasicInfoMap) {
            for (Map.Entry<String, Map<String, String>> userBasicInfoMapEntry : userBasicInfoMap.entrySet()) {
                String key = userBasicInfoMapEntry.getKey();
                Map<String, String> basicAttr = userBasicInfoMapEntry.getValue();
                if (StringUtils.isEmpty(key)) {
                    return baseResult.setError(CommonErrorCode.PARAM_ERROR, key);
                }
                String basicAttrEntryKey;
                String basicAttrEntryValue;
                for (Map.Entry<String, String> basicAttrEntry : basicAttr.entrySet()) {
                    basicAttrEntryKey = basicAttrEntry.getKey();
                    basicAttrEntryValue = basicAttrEntry.getValue();
                    if (StringUtils.isEmpty(basicAttrEntryKey) || StringUtils.isEmpty(basicAttrEntryValue)) {
                        return baseResult.setError(CommonErrorCode.PARAM_ERROR, basicAttrEntryKey + basicAttrEntryValue);
                    }
                }
            }
        }

        //校验添加的额外属性与模板规定的是否一致
        Map<String, List<Map<String, String>>> userExtendInfoMap = addParam.getUserExtendInfoMap();
        if (null != userExtendInfoMap) {
            for (Map.Entry<String, List<Map<String, String>>> extendEntry : userExtendInfoMap.entrySet()) {
                String key = extendEntry.getKey();
                List<Map<String, String>> subValueList = extendEntry.getValue();
                if (StringUtils.isEmpty(key)) {
                    return baseResult.setError(CommonErrorCode.PARAM_ERROR, key);
                }
                for (Map<String, String> map : subValueList) {
                    for (Map.Entry<String, String> subEntry : map.entrySet()) {
                        String subKey = subEntry.getKey();
                        String subValue = subEntry.getValue();
                        if (StringUtils.isEmpty(subKey) || StringUtils.isEmpty(subValue)) {
                            return baseResult.setError(CommonErrorCode.PARAM_ERROR, subKey + subValue);
                        }
                    }
                }
            }

        }

        //添加额外的基本信息
        addParam.getUserCommonInfo().setUserUuid(CommonUtils.getUuid());
        addParam.setDel(Boolean.FALSE);
        addParam.setCreateTime(new Date());
        addParam.setModifyTime(new Date());

        return userService.addUser(addParam);
    }

    @PostMapping("deleteUser")
    public Object deleteUser(@RequestBody UserDeleteParam deleteParam) {
        BaseResult baseResult = new BaseResult();
        if (null == deleteParam) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, Param.DELETE_PARAM.getParam());
        }
        List<String> userUuidList = deleteParam.getUserUuidList();
        for (String userUuid : userUuidList) {
            if (StringUtils.isEmpty(userUuid)) {
                return baseResult.setError(CommonErrorCode.PARAM_ERROR, UserField.USER_UUID.getField());
            }
        }
        return userService.deleteUser(deleteParam);
    }


    /**
     * 对于纯Json渲染的页面，保存走该接口
     *
     * @param addParam
     * @return
     */
    @PostMapping("addPureJsonTabInfo")
    public Object addPureJsonTabInfo(@RequestBody UserAddPureJsonTabInfoParam addParam) {
        PlainResult<String> plainResult = new PlainResult<>();

        if (addParam == null) {
            return plainResult.setError(CommonErrorCode.PARAM_ERROR, Param.ADD_PARAM.getParam());
        }

        if (StringUtils.isEmpty(addParam.getTabKey())) {
            return plainResult.setError(CommonErrorCode.PARAM_ERROR, Param.TAB_KEY.getParam());
        }

        if (StringUtils.isEmpty(StringUtils.isEmpty(addParam.getUserTemplateCode()))) {
            return plainResult.setError(CommonErrorCode.PARAM_ERROR, UserField.USER_TEMPLATE_CODE.getField());
        }

        //格式校验
        PlainResult<Map<String, String>> dataPlainResult = userTemplateCheckService.getKeyValueInfo(addParam.getPureJsonTabInfoMap());
        if (!dataPlainResult.isSuccess()) {
            return plainResult.setError(dataPlainResult.getCode(), dataPlainResult.getMessage());
        }

        Map<String, String> dataMap = dataPlainResult.getData();

        //如果是basic则添加一个用户，不是则是扩展
        String userUUID;
        User user = null;
        if (addParam.getTabKey().equals(UserField.USER_BASIC_TAB.getField())) {
            if (!dataMap.containsKey(UserField.USER_NAME.getField()) ||
                    StringUtils.isEmpty(dataMap.get(UserField.USER_NAME.getField()))) {
                return plainResult.setError(CommonErrorCode.PARAM_ERROR, UserField.USER_NAME.getField());
            }
            userUUID = CommonUtils.getUuid();
        } else {
            userUUID = addParam.getUserUuid();

            UserQueryByUuidListParam queryParam = new UserQueryByUuidListParam();
            List<String> userUuidList = new ArrayList<>();
            userUuidList.add(userUUID);
            queryParam.setUserUuidList(userUuidList);
            ListResult<User> listResult = userService.findUserByUuidList(queryParam);

            if (!listResult.isSuccess()) {
                return plainResult.setError(listResult.getCode(), listResult.getMessage());
            }
            List<User> sourceList = listResult.getData();
            if (sourceList.size() > Number.ONE.getNumber()) {
                return plainResult.setError(CommonErrorCode.HAS_EXIT, UserField.USER_UUID.getField(), sourceList.size());
            }
            if (sourceList.size() < Number.ONE.getNumber()) {
                return plainResult.setError(CommonErrorCode.NOT_EXIT, UserField.USER_UUID.getField(), userUUID);
            }
            user = sourceList.get(Number.ZERO.getNumber());
        }

        //校验用户模板是否存在
        UserTemplateQueryByCodeParam queryParam = new UserTemplateQueryByCodeParam();
        List<String> userTemplateCodeList = new ArrayList<>();
        userTemplateCodeList.add(addParam.getUserTemplateCode());
        queryParam.setUserTemplateCodeList(userTemplateCodeList);
        ListResult<UserTemplate> userTemplateListResult = userTemplateService.findUserTemplate(queryParam);

        if (!userTemplateListResult.isSuccess()) {
            return plainResult.setError(userTemplateListResult.getCode(), userTemplateListResult.getMessage());
        }

        List<UserTemplate> userTemplateList = userTemplateListResult.getData();
        if (CollectionUtils.isEmpty(userTemplateList)) {
            return plainResult.setError(CommonErrorCode.NOT_EXIT, UserField.USER_TEMPLATE_CODE.getField(), addParam.getUserTemplateCode());
        }

        if (userTemplateList.size() > Number.ONE.getNumber()) {
            return plainResult.setError(CommonErrorCode.HAS_EXIT, addParam.getUserTemplateCode(), userTemplateList.size());
        }

        UserTemplate userTemplate = userTemplateList.get(Number.ZERO.getNumber());

        //获取jsonValue
        PlainResult<String> checkPlainResult = userTemplateCheckService.getPureJsonTabStr(userTemplate, addParam.getTabKey());

        if (!checkPlainResult.isSuccess()) {
            return plainResult.setError(checkPlainResult.getCode(), checkPlainResult.getMessage());
        }

        String jsonValue = checkPlainResult.getData();

        Map<String, JSONObject> widgetsMap;
        //获取相对应的字段映射
        try {
            widgetsMap = userTemplateCheckService.getWidgetsMap(jsonValue);
        } catch (Exception e) {
            return plainResult.setError(CommonErrorCode.JSON_DATA_ERROR, e.toString());
        }

        if (CollectionUtils.isEmpty(widgetsMap)) {
            return plainResult.setError(CommonErrorCode.JSON_DATA_IS_ILLEGAL);
        }

        BaseResult baseResult = userTemplateCheckService.checkPureJsonInfo(widgetsMap, dataMap);

        if (!baseResult.isSuccess()) {
            return plainResult.setError(baseResult.getCode(), baseResult.getMessage());
        }

        baseResult = userService.addPureJsonTabInfo(addParam, userUUID, user, dataMap);
        if (!baseResult.isSuccess()) {
            return plainResult.setError(baseResult.getCode(), baseResult.getMessage());
        }

        plainResult.setData(userUUID);

        return plainResult;
    }


    /**
     * 对于纯Json渲染的页面，保存走该接口
     *
     * @param updateParam
     * @return
     */
    @PostMapping("updatePureJsonTabInfo")
    public Object updatePureJsonTabInfo(@RequestBody UserUpdatePureJsonTabInfoParam updateParam) {
        BaseResult baseResult = new BaseResult();

        if (updateParam == null) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, Param.UPDATE_PARAM.getParam());
        }

        if (StringUtils.isEmpty(updateParam.getTabKey())) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, Param.TAB_KEY.getParam());
        }

        if (StringUtils.isEmpty(updateParam.getUserTemplateCode())) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, UserField.USER_TEMPLATE_CODE.getField());
        }

        if (StringUtils.isEmpty(updateParam.getUserUuid())) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, UserField.USER_UUID.getField());
        }

        PlainResult<Map<String, String>> dataPlainResult = userTemplateCheckService.getKeyValueInfo(updateParam.getPureJsonTabInfoMap());

        if (!dataPlainResult.isSuccess()) {
            return baseResult.setError(dataPlainResult.getCode(), dataPlainResult.getMessage());
        }
        //获取解析后的数据
        Map<String, String> dataMap = dataPlainResult.getData();

        String userUuid = updateParam.getUserUuid();

        //查找user
        UserQueryByUuidListParam queryParam = new UserQueryByUuidListParam();
        List<String> userUuidList = new ArrayList<>();
        userUuidList.add(userUuid);
        queryParam.setUserUuidList(userUuidList);
        ListResult<User> listResult = userService.findUserByUuidList(queryParam);
        if (!listResult.isSuccess()) {
            return baseResult.setError(listResult.getCode(), listResult.getMessage());
        }

        if (listResult.getCount() > Number.ONE.getNumber()) {
            return baseResult.setError(CommonErrorCode.HAS_EXIT, TableName.USER.getTableName(), listResult.getCount());
        }
        if (listResult.getCount() < Number.ONE.getNumber()) {
            return baseResult.setError(CommonErrorCode.NOT_EXIT, UserField.USER_UUID.getField(), userUuid);
        }

        //进行tab分类考虑
        if (updateParam.getTabKey().equals(UserField.USER_BASIC_TAB.getField())) {
            if (!dataMap.containsKey(UserField.USER_NAME.getField())) {
                return baseResult.setError(CommonErrorCode.PARAM_ERROR, UserField.USER_NAME.getField());
            }
        }
        //校验传参的sourceTemplateCode是否存在且唯一
        UserTemplateQueryByCodeParam queryTemplateParam = new UserTemplateQueryByCodeParam();
        List<String> userTemplateCodeList = new ArrayList<>();
        userTemplateCodeList.add(updateParam.getUserTemplateCode());
        queryTemplateParam.setUserTemplateCodeList(userTemplateCodeList);
        ListResult<UserTemplate> userTemplateListResult = userTemplateService.findUserTemplate(queryTemplateParam);

        if (!userTemplateListResult.isSuccess()) {
            return baseResult.setError(userTemplateListResult.getCode(), userTemplateListResult.getMessage());
        }

        List<UserTemplate> userTemplateList = userTemplateListResult.getData();
        if (CollectionUtils.isEmpty(userTemplateList)) {
            return baseResult.setError(CommonErrorCode.NOT_EXIT, UserTemplateField.USER_TEMPLATE_CODE.getField(), updateParam.getUserTemplateCode());
        }

        if (userTemplateList.size() > Number.ONE.getNumber()) {
            return baseResult.setError(CommonErrorCode.HAS_EXIT, UserTemplateField.USER_TEMPLATE_CODE.getField(), updateParam.getUserTemplateCode());
        }

        UserTemplate userTemplate = userTemplateList.get(Number.ZERO.getNumber());

        PlainResult<String> checkPlainResult = userTemplateCheckService.getPureJsonTabStr(userTemplate, updateParam.getTabKey());

        if (!checkPlainResult.isSuccess()) {
            return baseResult.setError(checkPlainResult.getCode(), checkPlainResult.getMessage());
        }

        String jsonValue = checkPlainResult.getData();

        Map<String, JSONObject> widgetsMap = new HashMap<>();

        try {
            widgetsMap = userTemplateCheckService.getWidgetsMap(jsonValue);
        } catch (Exception e) {
            return baseResult.setError(CommonErrorCode.JSON_DATA_ERROR, e.toString());
        }

        if (!CollectionUtils.isEmpty(widgetsMap)) {
            BaseResult innerBaseResult = userTemplateCheckService.checkPureJsonInfo(widgetsMap, dataMap);

            if (!innerBaseResult.isSuccess()) {
                return innerBaseResult;
            }
        }

        baseResult = userService.updatePureJsonTabInfo(updateParam, userUuid, dataMap);
        return baseResult;
    }

    /**
     * 存储扩展属性
     *
     * @param addParam
     * @return
     */
    @PostMapping("addExtendTabInfo")
    public Object addExtendTabInfo(@RequestBody UserAddExtendTabInfoParam addParam) {
        BaseResult baseResult = new BaseResult();
        if (null == addParam) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, Param.ADD_PARAM.getParam());
        }

        if (StringUtils.isEmpty(addParam.getTabKey())) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, Param.TAB_KEY.getParam(), addParam.getTabKey());
        }
        if (StringUtils.isEmpty(addParam.getUserTemplateCode())) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, UserField.USER_TEMPLATE_CODE.getField(), addParam.getUserTemplateCode());
        }
        if (StringUtils.isEmpty(addParam.getUserUuid())) {
            return baseResult.setError(CommonErrorCode.PARAM_ERROR, UserField.USER_UUID.getField(), addParam.getUserUuid());
        }
        addParam.setDataUuid(CommonUtils.getUuid());

        //前端数据转换与校验
        PlainResult<Map<String, String>> dataPlainResult = userTemplateCheckService.getKeyValueInfo(addParam.getExtendInfoMap());
        if (!dataPlainResult.isSuccess()) {
            return baseResult.setError(dataPlainResult.getCode(), dataPlainResult.getMessage());
        }
        Map<String, String> dataMap = dataPlainResult.getData();

        //查询user
        UserQueryByUuidListParam queryParam = new UserQueryByUuidListParam();
        List<String> userUuidList = new ArrayList<>();
        userUuidList.add(addParam.getUserUuid());
        queryParam.setUserUuidList(userUuidList);
        ListResult<User> listResult = userService.findUserByUuidList(queryParam);

        if (!listResult.isSuccess()) {
            return baseResult.setError(listResult.getCode(), listResult.getMessage());
        }

        List<User> userList = listResult.getData();
        if (userList.size() > Number.ONE.getNumber()) {
            return baseResult.setError(CommonErrorCode.HAS_EXIT, UserField.USER_UUID.getField(), addParam.getUserUuid());
        }
        if (userList.size() < Number.ONE.getNumber()) {
            return baseResult.setError(CommonErrorCode.NOT_EXIT, UserField.USER_UUID.getField(), addParam.getUserUuid());
        }
        User user = userList.get(Number.ZERO.getNumber());

        //校验传参的sourceTemplateCode是否存在且唯一
        UserTemplateQueryByCodeParam queryTemplateParam = new UserTemplateQueryByCodeParam();
        List<String> userTemplateCodeList = new ArrayList<>();
        userTemplateCodeList.add(addParam.getUserTemplateCode());
        queryTemplateParam.setUserTemplateCodeList(userTemplateCodeList);
        ListResult<UserTemplate> userTemplateListResult = userTemplateService.findUserTemplate(queryTemplateParam);

        if (!userTemplateListResult.isSuccess()) {
            return baseResult.setError(userTemplateListResult.getCode(), userTemplateListResult.getMessage());
        }

        List<UserTemplate> userTemplateList = userTemplateListResult.getData();
        if (CollectionUtils.isEmpty(userTemplateList)) {
            return baseResult.setError(CommonErrorCode.NOT_EXIT, UserTemplateField.USER_TEMPLATE_CODE.getField(), addParam.getUserTemplateCode());
        }

        if (userTemplateList.size() > Number.ONE.getNumber()) {
            return baseResult.setError(CommonErrorCode.HAS_EXIT, UserTemplateField.USER_TEMPLATE_CODE.getField(), addParam.getUserTemplateCode());
        }

        UserTemplate userTemplate = userTemplateList.get(Number.ZERO.getNumber());


        //获取模板中表格页面结构数据
        PlainResult<List<String>> tabPlainResult = userTemplateCheckService.getTableTabStr(userTemplate, addParam.getTabKey());

        if (!tabPlainResult.isSuccess()) {
            return baseResult.setError(tabPlainResult.getCode(), tabPlainResult.getMessage());
        }

        List<String> keyLabelList = tabPlainResult.getData();

        //获取表格结构数据中key到label的映射
        PlainResult<Map<String, String>> keyLabelMapPlainResult = userTemplateCheckService.getTabKeyLabelMap(keyLabelList);

        if (!keyLabelMapPlainResult.isSuccess()) {
            return baseResult.setError(keyLabelMapPlainResult.getCode(), keyLabelMapPlainResult.getMessage());
        }

        Map<String, String> keyLabelMap = keyLabelMapPlainResult.getData();

        //校验模板里的信息
        PlainResult<String> jsonPlainResult = userTemplateCheckService.getPureJsonTabStr(userTemplate, addParam.getTabKey());

        if (!jsonPlainResult.isSuccess()) {
            return baseResult.setError(jsonPlainResult.getCode(), jsonPlainResult.getMessage());
        }

        String jsonValue = jsonPlainResult.getData();

        Map<String, JSONObject> widgetsMap = new HashMap<>();

        try {
            widgetsMap = userTemplateCheckService.getWidgetsMap(jsonValue);
        } catch (Exception e) {
            return baseResult.setError(CommonErrorCode.JSON_DATA_ERROR, e.toString());
        }

        if (!CollectionUtils.isEmpty(widgetsMap)) {
            baseResult = userTemplateCheckService.checkTableTabInfo(widgetsMap, keyLabelMap, dataMap);

            if (!baseResult.isSuccess()) {
                return baseResult;
            }
        }

        return userService.addExtendTabInfo(addParam, user, userTemplate, dataMap);
    }

    @PostMapping("queryAll")
    public Object queryAllSiteCodeToItem() {
        PlainResult<Map<String, UserApiVo>> plainResult = new PlainResult<>();


        return plainResult;
    }
}
