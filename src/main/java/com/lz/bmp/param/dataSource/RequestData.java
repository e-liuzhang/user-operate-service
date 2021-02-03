package com.lz.bmp.param.dataSource;


/**
 * @author rocky
 * @ClassName RequestData
 * @Description
 * @Create by rocky
 * @Date 2020/11/16 上午10:48
 */
public class RequestData {

    /**
     * 数据源名称
     */
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    @Override
    public String toString() {
        return "RequestData{" +
                "code='" + code + '\'' +
                '}';
    }
}
