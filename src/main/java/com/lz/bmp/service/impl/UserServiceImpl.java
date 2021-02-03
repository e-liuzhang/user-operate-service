package com.lz.bmp.service.impl;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.lz.bmp.dao.UserDao;
import com.lz.bmp.entity.user.User;
import com.lz.bmp.entity.userTemplate.UserTemplate;
import com.lz.bmp.param.user.*;
import com.lz.bmp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 14:24
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;


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
    public ListResult<Map<String, String>> getExtendInfoList(UserQueryExtendInfoParam queryParam, User user, UserTemplate userTemplate) {
        return userDao.getExtendInfoList(queryParam, user, userTemplate);
    }
}
