package com.lz.bmp.param.user;

/**
 * @Author shangang_luo
 * @Date 2021/2/4 15:02
 */

public class UserQueryByUuidParam {

    private String userUuid;

    public String getUserUuid() {
        return userUuid;
    }

    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid;
    }

    @Override
    public String toString() {
        return "UserQueryByUuidParam{" +
                "userUuid='" + userUuid + '\'' +
                '}';
    }
}
