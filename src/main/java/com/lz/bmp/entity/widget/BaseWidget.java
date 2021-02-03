package com.lz.bmp.entity.widget;

/**
 * 基本控件类
 *
 * @author shangang_luo
 *
 */
public class BaseWidget {

    private String type;

    private String fieldNo;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFieldNo() {
        return fieldNo;
    }

    public void setFieldNo(String fieldNo) {
        this.fieldNo = fieldNo;
    }

    @Override
    public String toString() {
        return "BaseWidget{" +
                "type='" + type + '\'' +
                ", fieldNo='" + fieldNo + '\'' +
                '}';
    }
}
