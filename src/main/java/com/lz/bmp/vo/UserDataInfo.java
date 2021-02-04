package com.lz.bmp.vo;

import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/2/4 15:04
 */

public class UserDataInfo {

    /**
     * 存储keyvalue类型的值
     */
    private Map<String, String> basicValue;

    /**
     * 存储扩展属性的值
     */
    private List<Map<String, String>> extendValue;

    public Map<String, String> getBasicValue() {
        return basicValue;
    }

    public void setBasicValue(Map<String, String> basicValue) {
        this.basicValue = basicValue;
    }

    public List<Map<String, String>> getExtendValue() {
        return extendValue;
    }

    public void setExtendValue(List<Map<String, String>> extendValue) {
        this.extendValue = extendValue;
    }

    @Override
    public String toString() {
        return "UserDataInfo{" +
                "basicValue=" + basicValue +
                ", extendValue=" + extendValue +
                '}';
    }
}
