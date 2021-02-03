package com.lz.bmp.param.user;

import java.util.List;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 14:29
 */

public class UserDeleteParam {

    private List<String> userUuidList;

    public List<String> getUserUuidList() {
        return userUuidList;
    }

    public void setUserUuidList(List<String> userUuidList) {
        this.userUuidList = userUuidList;
    }

    @Override
    public String toString() {
        return "UserDeleteParam{" +
                "userUuidList=" + userUuidList +
                '}';
    }
}
