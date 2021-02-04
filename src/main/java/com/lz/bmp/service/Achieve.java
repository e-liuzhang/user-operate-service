package com.lz.bmp.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author rocky
 * @ClassName achieve
 * @Description
 * @Create by rocky
 * @Date 2020/11/2 下午5:20
 */
public interface Achieve {


    /**
     * 获取数据源数据
     * @param url 请求的url
     * @param parameter 路参 格式{item} = {item}
     * @param param 参数
     * @param accessMode 访问方式
     * @return
     */
    public Object achieveData(String url, String parameter, JSONObject param, String accessMode);
}
