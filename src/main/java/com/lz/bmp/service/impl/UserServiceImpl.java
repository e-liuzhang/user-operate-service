package com.lz.bmp.service.impl;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.dao.UserDao;
import com.lz.bmp.dataenum.Number;
import com.lz.bmp.entity.user.User;
import com.lz.bmp.entity.userTemplate.UserTemplate;
import com.lz.bmp.entity.userTemplate.UserTemplateTab;
import com.lz.bmp.param.user.*;
import com.lz.bmp.service.UserService;
import com.lz.bmp.service.UserTemplateCheckService;
import com.lz.bmp.service.UserTemplateService;
import com.lz.bmp.vo.UserContentVo;
import com.lz.bmp.vo.UserDataInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 14:24
 */

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;


    @Autowired
    private UserTemplateCheckService userTemplateCheckService;


    @Override
    public BaseResult addUser(UserAddForThreePartyParam addParam) {
        return userDao.addUser(addParam);
    }

    @Override
    public BaseResult deleteUser(UserDeleteParam deleteParam) {
        return userDao.deleteUser(deleteParam);
    }

    @Override
    public ListResult<User> findUserByUuidList(UserQueryByUuidListParam queryParam) {
        return userDao.findUserByUuidList(queryParam);
    }

    @Override
    public BaseResult addPureJsonTabInfo(UserAddPureJsonTabInfoParam addParam, String userUUID, User user, Map<String, String> dataMap) {
        return userDao.addPureJsonTabInfo(addParam, userUUID, user, dataMap);
    }

    @Override
    public BaseResult updatePureJsonTabInfo(UserUpdatePureJsonTabInfoParam updateParam, String userUuid, Map<String, String> dataMap) {
        return userDao.updatePureJsonTabInfo(updateParam, userUuid, dataMap);
    }

    @Override
    public BaseResult addExtendTabInfo(UserAddExtendTabInfoParam addParam, User user, UserTemplate userTemplate, Map<String, String> dataMap) {
        return userDao.addExtendTabInfo(addParam, user, userTemplate, dataMap);
    }

    @Override
    public BaseResult updateExtendTabInfo(UserUpdateExtendTabInfoParam updateParam, User user, UserTemplate userTemplate, Map<String, String> dataMap) {
        return userDao.updateExtendTabInfo(updateParam, user, userTemplate, dataMap);
    }

    @Override
    public BaseResult deleteExtendTabInfo(UserDeleteExtendTabInfoParam deleteParam, User user, UserTemplate userTemplate) {
        return userDao.deleteExtendTabInfo(deleteParam, user, userTemplate);
    }

    @Override
    public PlainResult<UserContentVo> getUser(User user, UserTemplate userTemplate) {
        PlainResult<UserContentVo> plainResult = new PlainResult<>();

        UserContentVo userPageVo = new UserContentVo();

        Map<String, UserDataInfo> userDataMap = new HashMap<>();

        if (user == null) {
            userPageVo.setUserDataMap(userDataMap);
            userPageVo.setUserTemplateCommonAttr(userTemplate.getUserTemplateCommonAttr());
            userPageVo.setUserTemplateDataMap(userTemplate.getUserTemplateDataMap());
            userPageVo.setUserTemplateTabList(userTemplate.getUserTemplateTabList());
            plainResult.setData(userPageVo);
            return plainResult;
        }

        List<UserTemplateTab> userTemplateTabList = userTemplate.getUserTemplateTabList();
        try {
            for (UserTemplateTab tab : userTemplateTabList) {

                if (Integer.valueOf(Number.ONE.getNumber()).equals(tab.getTabType())) {

                    PlainResult<String> jsonPlainResult = userTemplateCheckService.getPureJsonTabStr(userTemplate, tab.getTabKey());
                    if (!jsonPlainResult.isSuccess()) {
                        plainResult.setCode(jsonPlainResult.getCode());
                        plainResult.setMessage(jsonPlainResult.getMessage());
                        plainResult.setSuccess(false);
                        return plainResult;
                    }
                    String jsonValue = jsonPlainResult.getData();

                    UserDataInfo userDataInfo = new UserDataInfo();
                    Map<String, String> basicValue = user.getUserBasicInfoMap().get(tab.getTabKey());
                    PlainResult<Map<String, String>> pagePlainResult = userTemplateCheckService.getPageBasicValue(basicValue, jsonValue);
                    if (!pagePlainResult.isSuccess()) {
                        plainResult.setCode(pagePlainResult.getCode());
                        plainResult.setMessage(pagePlainResult.getMessage());
                        plainResult.setSuccess(false);
                        return plainResult;
                    }
                    userDataInfo.setBasicValue(pagePlainResult.getData());
                    userDataMap.put(tab.getTabKey(), userDataInfo);
                } else {
                    PlainResult<List<String>> tablePlainResult = userTemplateCheckService.getTableTabStr(userTemplate, tab.getTabKey());
                    if (!tablePlainResult.isSuccess()) {
                        return plainResult.setError(plainResult.getCode(), plainResult.getMessage());
                    }

                    PlainResult<String> jsonPlainResult = userTemplateCheckService.getPureJsonTabStr(userTemplate, tab.getTabKey());
                    if (!jsonPlainResult.isSuccess()) {
                        return plainResult.setError(plainResult.getCode(), plainResult.getMessage());
                    }

                    String jsonValue = jsonPlainResult.getData();

                    UserDataInfo userDataInfo = new UserDataInfo();
                    List<Map<String, String>> extendValue = user.getUserExtendInfoMap().get(tab.getTabKey());
                    if (!CollectionUtils.isEmpty(extendValue)) {
                        ListResult<Map<String, String>> pageListResult = userTemplateCheckService.getPageExtendValue(extendValue,
                                jsonValue, tab.getTabKey());
                        if (!pageListResult.isSuccess()) {
                            return plainResult.setError(plainResult.getCode(), plainResult.getMessage());
                        }
                        userDataInfo.setExtendValue(pageListResult.getData());
                        userDataMap.put(tab.getTabKey(), userDataInfo);
                    } else {
                        userDataInfo.setExtendValue(new ArrayList<>());
                        userDataMap.put(tab.getTabKey(), userDataInfo);
                    }
                }
            }
        } catch (Exception e) {
            logger.error(String.format("user: %s, userTemplate: %s, Reason: %s, ExceptionStackTrace: %s",
                    user, userTemplate, "Failed to getUser", e.toString()));
        }

        userPageVo.setUserDataMap(userDataMap);
        userPageVo.setUserTemplateCommonAttr(userTemplate.getUserTemplateCommonAttr());
        userPageVo.setUserTemplateDataMap(userTemplate.getUserTemplateDataMap());
        userPageVo.setUserTemplateTabList(userTemplate.getUserTemplateTabList());

        plainResult.setData(userPageVo);

        return plainResult;
    }

}
