package com.lz.bmp.dao;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.lz.bmp.entity.user.User;
import com.lz.bmp.param.user.UserAddForThreePartyParam;
import com.lz.bmp.param.user.UserAddPureJsonTabInfoParam;
import com.lz.bmp.param.user.UserDeleteParam;
import com.lz.bmp.param.user.UserQueryByUuidListParam;

import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 12:04
 */

public interface UserDao {
    /**
     * 增加用户数据
     * @param addParam
     * @return
     */
    BaseResult addUser(UserAddForThreePartyParam addParam);

    /**
     * 删除用户（伪删除）
     *
     * @param deleteParam
     * @return
     */
    BaseResult deleteUser(UserDeleteParam deleteParam);

    /**
     * 根据UUID查找用户
     *
     * @param queryParam
     * @return
     */
    ListResult<User> findUserByUuidList(UserQueryByUuidListParam queryParam);

    /**
     * 增加全部的tab信息
     *
     * @param addParam
     * @param userUUID
     * @param user
     * @param dataMap
     * @return
     */
    BaseResult addPureJsonTabInfo(UserAddPureJsonTabInfoParam addParam, String userUUID, User user, Map<String, String> dataMap);


}
