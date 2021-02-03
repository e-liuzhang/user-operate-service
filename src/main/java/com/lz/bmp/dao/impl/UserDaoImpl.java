package com.lz.bmp.dao.impl;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.lz.bmp.dao.UserDao;
import com.lz.bmp.dataenum.CommonErrorCode;
import com.lz.bmp.dataenum.TableName;
import com.lz.bmp.dataenum.UserField;
import com.lz.bmp.dataenum.UserStatus;
import com.lz.bmp.entity.user.User;
import com.lz.bmp.entity.user.UserCommonInfo;
import com.lz.bmp.param.user.UserAddForThreePartyParam;
import com.lz.bmp.param.user.UserAddPureJsonTabInfoParam;
import com.lz.bmp.param.user.UserDeleteParam;
import com.lz.bmp.param.user.UserQueryByUuidListParam;
import com.lz.bmp.utils.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 12:05
 */

@Repository
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public BaseResult addUser(UserAddForThreePartyParam addParam) {
        BaseResult baseResult = new BaseResult();
        try {
            mongoTemplate.insert(addParam, TableName.USER.getTableName());
        } catch (Exception e) {
            return baseResult.setError(CommonErrorCode.ADD_ERROR, e.toString());
        }
        return baseResult;
    }

    @Override
    public BaseResult deleteUser(UserDeleteParam deleteParam) {
        BaseResult baseResult = new BaseResult();
        List<String> userUuidList = deleteParam.getUserUuidList();
        String index = UserField.USER_COMMON_INFO.getField() + CommonUtils.POINT + UserField.USER_UUID.getField();
        Query query = new Query();
        try {
            Criteria criteria = Criteria.where(index).in(userUuidList);
            query.addCriteria(criteria);
            Update update = new Update();
            update.set(UserField.IS_DEL.getField(), UserStatus.DELETED.getDel());
            mongoTemplate.updateMulti(query, update, TableName.USER.getTableName());
        } catch (Exception e) {
            baseResult.setError(CommonErrorCode.DELETE_ERROR, e.toString());
        }
        return baseResult;
    }

    @Override
    public ListResult<User> findUserByUuidList(UserQueryByUuidListParam queryParam) {
        ListResult<User> listResult = new ListResult<>();
        try {
            Query query = new Query();
            List<String> userUuidList = queryParam.getUserUuidList();
            String indexCode = UserField.USER_COMMON_INFO.getField() + CommonUtils.POINT +
                    UserField.USER_UUID.getField();
            Criteria criteria = Criteria.where(indexCode).in(userUuidList)
                    .and(UserField.IS_DEL.getField()).is(UserStatus.USING.getDel());
            query.addCriteria(criteria);
            List<User> userList = mongoTemplate.find(query, User.class, TableName.USER.getTableName());
            List<User> orderSourceList = new ArrayList<>();
            for (String userUuid : userUuidList) {
                for (User user : userList) {
                    if (user.getUserCommonInfo().getUserUuid().equals(userUuid) && !orderSourceList.contains(user)) {
                        orderSourceList.add(user);
                    }
                }
            }
            listResult.setData(orderSourceList);
            listResult.setCount(orderSourceList.size());
        } catch (Exception e) {
            listResult.setError(CommonErrorCode.QUERY_ERROR, e.toString());
        }
        return listResult;
    }

    @Override
    public BaseResult addPureJsonTabInfo(UserAddPureJsonTabInfoParam addParam, String userUUID, User user, Map<String, String> dataMap) {
        BaseResult baseResult = new BaseResult();
        try {
            if (addParam.getTabKey().equals(UserField.USER_BASIC_TAB.getField())) {
                User newUser = new User();
                UserCommonInfo userCommonInfo = new UserCommonInfo();
                userCommonInfo.setUserTemplateCode(addParam.getUserTemplateCode());
                userCommonInfo.setUserUuid(userUUID);
                Map<String, Map<String, String>> basicDataMap = new HashMap<>();
                basicDataMap.put(UserField.USER_BASIC_TAB.getField(), dataMap);
                newUser.setUserCommonInfo(userCommonInfo);
                newUser.setSourceBasicInfoMap(basicDataMap);
                newUser.setUserExtendInfoMap(new HashMap<>());
                newUser.setDel(false);
                mongoTemplate.insert(newUser, TableName.USER.getTableName());
            } else {
                Query query = new Query();
                String indexUuid = UserField.USER_COMMON_INFO.getField() + CommonUtils.POINT +
                        UserField.USER_UUID.getField();
                Update update = new Update();
                update.set(UserField.USER_BASIC_INFO_MAP.getField() + CommonUtils.POINT + addParam.getTabKey(),
                        dataMap);
                Criteria criteria = Criteria.where(indexUuid).is(userUUID);
                query.addCriteria(criteria);
                mongoTemplate.updateFirst(query, update, TableName.USER.getTableName());
            }
        } catch (Exception e) {
            logger.error(String.format("addParam: %s, Reason: %s, ExceptionStackTrace: %s",
                    addParam.toString(), "Failed to addPureJsonTabInfo", e.toString()));
            baseResult.setError(CommonErrorCode.ADD_ERROR, e.toString());
        }
        return baseResult;
    }
}
