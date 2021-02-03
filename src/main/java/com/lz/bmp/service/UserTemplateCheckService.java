package com.lz.bmp.service;

import com.alibaba.fastjson.JSONObject;
import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.ListResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.entity.userTemplate.UserTemplate;

import java.util.List;
import java.util.Map;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 15:15
 */

public interface UserTemplateCheckService {

    /**
     * 从前端的控件类型_字段结构中，提炼出字段到值的映射
     *
     * @param pureJsonTabInfoMap
     * @return
     * @throws Exception
     */
    PlainResult<Map<String, String>> getKeyValueInfo(Map<String, String> pureJsonTabInfoMap);

    /**
     * 获取模板中页面结构数据
     *
     * @param userTemplate
     * @param tabKey
     * @return
     */
    PlainResult<String> getPureJsonTabStr(UserTemplate userTemplate, String tabKey);

    /**
     * 解析json模板，得到fieldNo到具体空间信息的映射
     *
     * @param data
     * @return
     */
    Map<String, JSONObject> getWidgetsMap(String data);

    /**
     * 检查前端传入的值，是否满足模板格式要求
     *
     * @param widgetsMap
     * @param dataMap
     * @return
     */
    BaseResult checkPureJsonInfo(Map<String, JSONObject> widgetsMap, Map<String, String> dataMap);

    /**
     * 获取模板中表格页面结构数据
     *
     * @param userTemplate
     * @param tabKey
     * @return
     */
    PlainResult<List<String>> getTableTabStr(UserTemplate userTemplate, String tabKey);

    /**
     * 获取表格结构数据中key到label的映射
     *
     * @param tabKeyLabel
     * @return
     */
    PlainResult<Map<String, String>> getTabKeyLabelMap(List<String> tabKeyLabel);

    /**
     * 校验表格页面数据和结构的正确性
     *
     * @param keyLabelMap
     * @param dataMap
     * @return
     */
    BaseResult checkTableTabInfo(Map<String, JSONObject> widgetsMap, Map<String, String> keyLabelMap, Map<String, String> dataMap);

    /**
     * 数据库字段到值的映射，需要转换为前端识别的类型
     * “siteName”: “超级站”，需要转化为“input_siteName”: “超级站”
     *
     * @param extendValue
     * @param jsonValue
     * @return
     */
    ListResult<Map<String, String>> getPageExtendValue(List<Map<String, String>> extendValue, String jsonValue, String tabKey);

    /**
     * 在控件中获取从value到Label的的映射
     *
     * @param widgetsMap
     * @return
     */
    PlainResult<Map<String, Map<String, String>>> widgetValueToLabelMap(Map<String, JSONObject> widgetsMap);
}
