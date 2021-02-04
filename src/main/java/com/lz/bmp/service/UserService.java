package com.lz.bmp.service;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.entity.user.User;
import com.lz.bmp.entity.userTemplate.UserTemplate;
import com.lz.bmp.param.user.*;
import com.lz.bmp.vo.UserContentVo;

import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 11:55
 */

public interface UserService {


    /**
     * 新增用户
     * @param
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

    /**
     * 更新全部的tab信息
     *
     * @param updateParam
     * @param userUuid
     * @param dataMap
     * @return
     */
    BaseResult updatePureJsonTabInfo(UserUpdatePureJsonTabInfoParam updateParam, String userUuid, Map<String, String> dataMap);

    /**
     * 存储扩展属性
     *
     * @param addParam
     * @return
     */
    BaseResult addExtendTabInfo(UserAddExtendTabInfoParam addParam, User user, UserTemplate userTemplate, Map<String, String> dataMap);

    /**
     * 更新扩展属性
     *
     * @param updateParam
     * @param user
     * @param userTemplate
     * @param dataMap
     * @return
     */
    BaseResult updateExtendTabInfo(UserUpdateExtendTabInfoParam updateParam, User user, UserTemplate userTemplate, Map<String, String> dataMap);

    /**
     * 删除扩展属性
     *
     * @param deleteParam
     * @param user
     * @param userTemplate
     * @return
     */
    BaseResult deleteExtendTabInfo(UserDeleteExtendTabInfoParam deleteParam, User user, UserTemplate userTemplate);

    /**
     * 获取用户
     *
     * @param user
     * @param userTemplate
     * @return
     */
    PlainResult<UserContentVo> getUser(User user, UserTemplate userTemplate);

}
