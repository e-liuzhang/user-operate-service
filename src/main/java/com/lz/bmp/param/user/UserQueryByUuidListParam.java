package com.lz.bmp.param.user;

import java.util.List;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 15:34
 */

public class UserQueryByUuidListParam {

    private List<String> userUuidList;

    public List<String> getUserUuidList() {
        return userUuidList;
    }

    public void setUserUuidList(List<String> userUuidList) {
        this.userUuidList = userUuidList;
    }

    @Override
    public String toString() {
        return "UserQueryByUuidListParam{" +
                "userUuidList=" + userUuidList +
                '}';
    }
}
