package com.lz.bmp.service.impl;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.lz.bmp.dao.UserDao;
import com.lz.bmp.entity.user.User;
import com.lz.bmp.param.user.UserAddForThreePartyParam;
import com.lz.bmp.param.user.UserAddPureJsonTabInfoParam;
import com.lz.bmp.param.user.UserDeleteParam;
import com.lz.bmp.param.user.UserQueryByUuidListParam;
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
}
