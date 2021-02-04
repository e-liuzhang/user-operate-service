package com.lz.bmp.dao.impl;

import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.dao.UserDao;
import com.lz.bmp.dataenum.*;
import com.lz.bmp.dataenum.Number;
import com.lz.bmp.entity.user.User;
import com.lz.bmp.entity.user.UserCommonInfo;
import com.lz.bmp.entity.userTemplate.UserTemplate;
import com.lz.bmp.param.user.*;
import com.lz.bmp.utils.CommonUtils;
import com.mongodb.BasicDBObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

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
                newUser.setUserBasicInfoMap(basicDataMap);
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

    @Override
    public BaseResult updatePureJsonTabInfo(UserUpdatePureJsonTabInfoParam updateParam, String userUuid, Map<String, String> dataMap) {
        BaseResult baseResult = new BaseResult();

        String indexUuid = UserField.USER_COMMON_INFO.getField() + CommonUtils.POINT +
                UserField.USER_UUID.getField();
        Criteria criteria = Criteria.where(indexUuid).is(userUuid);
        Query query = new Query();
        query.addCriteria(criteria);
        try {
            Update update = new Update();
            update.set(UserField.USER_BASIC_INFO_MAP.getField() + CommonUtils.POINT + updateParam.getTabKey(),
                    dataMap);
            mongoTemplate.updateFirst(query, update, TableName.USER.getTableName());
        } catch (Exception e) {
            logger.error(String.format("updateParam: %s, userUuid: %s, Reason: %s, ExceptionStackTrace: %s",
                    updateParam.toString(), userUuid, "Failed to updatePureJsonTabInfo", e.toString()));
            baseResult.setError(CommonErrorCode.UPDATE_ERROR, e.toString());
        }
        return baseResult;
    }

    @Override
    public BaseResult addExtendTabInfo(UserAddExtendTabInfoParam addParam, User user, UserTemplate userTemplate, Map<String, String> dataMap) {
        BaseResult baseResult = new BaseResult();
        Map<String, List<Map<String, String>>> userExtendInfoMap = user.getUserExtendInfoMap();

        Map<String, String> extendInfo = new HashMap<>();
        extendInfo.put(UserField.DATA_UUID.getField(), addParam.getDataUuid());
        extendInfo.putAll(dataMap);

        Query query = new Query();
        Criteria criteria = Criteria.where(UserField.USER_COMMON_INFO.getField() + CommonUtils.POINT +
                UserField.USER_UUID.getField()).is(addParam.getUserUuid());
        query.addCriteria(criteria);

        String tabKey = addParam.getTabKey();

        if (StringUtils.isEmpty(tabKey)) {
            return baseResult.setError(CommonErrorCode.TAB_KEY_NOT_EXIT, tabKey);
        }

        Update update = new Update();

        try {
            if (userExtendInfoMap.containsKey(tabKey)) {

                update.addToSet(UserField.USER_EXTEND_INFO_MAP.getField() + CommonUtils.POINT + tabKey,
                        extendInfo);

                mongoTemplate.upsert(query, update, TableName.USER.getTableName());
            } else {
                List<Map<String, String>> extendInfoTab = new ArrayList<>();
                extendInfoTab.add(extendInfo);

                update.set(UserField.USER_EXTEND_INFO_MAP.getField() + CommonUtils.POINT +
                        tabKey, extendInfoTab);
                int res = (int) mongoTemplate.updateFirst(query, update, TableName.USER.getTableName()).getModifiedCount();
                if (res < Number.ONE.getNumber()) {
                    return baseResult.setError(CommonErrorCode.UPDATE_ERROR, res);
                }
            }
        } catch (Exception e) {
            baseResult.setError(CommonErrorCode.UPDATE_ERROR, e.toString());
        }

        return baseResult;
    }

    @Override
    public BaseResult updateExtendTabInfo(UserUpdateExtendTabInfoParam updateParam, User user, UserTemplate userTemplate, Map<String, String> dataMap) {
        BaseResult baseResult = new BaseResult();

        //校验Code是否存在并且获取该uiid在其下的下标
        Integer entendIndex = null;
        String tabKey = updateParam.getTabKey();

        //额外信息数组
        ArrayList<Map<String, String>> entendInfo = (ArrayList<Map<String, String>>) user.getUserExtendInfoMap().get(tabKey);
        if (null == entendInfo) {
            return baseResult.setError(CommonErrorCode.NOT_EXIT, Param.TAB_KEY.getParam(), tabKey);
        }

        for (int i = 0; i < entendInfo.size(); i++) {
            if (entendInfo.get(i).get(UserField.DATA_UUID.getField()).equals(updateParam.getDataUuid())) {
                entendIndex = i;
            }
        }
        if (entendIndex == null) {
            return baseResult.setError(CommonErrorCode.NOT_EXIT, UserField.DATA_UUID.getField(), updateParam.getDataUuid());
        }

        Query query = new Query();
        Criteria criteria = Criteria.where(UserField.USER_COMMON_INFO.getField() + CommonUtils.POINT +
                UserField.USER_UUID.getField()).is(updateParam.getUserUuid());
        query.addCriteria(criteria);

        //根据数组下标进行更新
        dataMap.put(UserField.DATA_UUID.getField(), updateParam.getDataUuid());
        try {
            Update update = new Update();
            update.set(UserField.USER_EXTEND_INFO_MAP.getField() + CommonUtils.POINT + tabKey +
                    CommonUtils.POINT + entendIndex, dataMap);
            int res = (int) mongoTemplate.updateFirst(query, update, User.class).getModifiedCount();

            if (res < Number.ONE.getNumber()) {
                return baseResult.setError(CommonErrorCode.UPDATE_ERROR, res);
            }
        }catch (Exception e){
            baseResult.setError(CommonErrorCode.UPDATE_ERROR, e.toString());
        }

        return baseResult;
    }

    @Override
    public BaseResult deleteExtendTabInfo(UserDeleteExtendTabInfoParam deleteParam, User user, UserTemplate userTemplate) {
        BaseResult baseResult = new BaseResult();
        //校验Code是否存在并且获取该uiid在其下的下标
        Integer entendIndex = null;

        String tabKey = deleteParam.getTabKey();

        for (String dataUuid : deleteParam.getDataUuidList()) {
            Query query = new Query();
            Criteria criteria = Criteria.where(UserField.USER_COMMON_INFO.getField() + CommonUtils.POINT + UserField.USER_UUID.getField()).is(deleteParam.getUserUuid());

            query.addCriteria(criteria);

            try {
                Update update = new Update();
                BasicDBObject basicDb = new BasicDBObject();
                basicDb.put(UserField.DATA_UUID.getField(), dataUuid);

                update.pull(UserField.USER_EXTEND_INFO_MAP.getField() + CommonUtils.POINT + tabKey, basicDb);

                int res = (int) mongoTemplate.updateFirst(query, update, User.class).getModifiedCount();

                if (res < Number.ONE.getNumber()) {
                    //这里继续执行其他user数据
                    baseResult.setError(CommonErrorCode.DELETE_ERROR, res);
                }
            }catch (Exception e){
                return baseResult.setError(CommonErrorCode.UPDATE_ERROR, e.toString());
            }

        }

        return baseResult;
    }

}
