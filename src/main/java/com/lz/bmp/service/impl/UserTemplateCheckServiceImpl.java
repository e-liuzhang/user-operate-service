package com.lz.bmp.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fpi.simple.result.BaseResult;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.dataenum.CommonErrorCode;
import com.lz.bmp.dataenum.Param;
import com.lz.bmp.entity.userTemplate.UserTemplate;
import com.lz.bmp.entity.userTemplate.UserTemplateInfo;
import com.lz.bmp.entity.userTemplate.UserTemplateTab;
import com.lz.bmp.entity.widget.*;
import com.lz.bmp.dataenum.widget.*;
import com.lz.bmp.service.UserTemplateCheckService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * @Author shangang_luo
 * @Date 2021/2/1 15:16
 */

@Repository
public class UserTemplateCheckServiceImpl implements UserTemplateCheckService {

    private static final Logger logger = LoggerFactory.getLogger(UserTemplateCheckServiceImpl.class);

    public static final Map<String, Object> WIDGET_NAME_TO_WIDGET_CLASS = new HashMap<>();

    static {
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.INPUT.getJsonWidgetTypeName(), new InputWidget());
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.TEXTAREA.getJsonWidgetTypeName(), new TextareaWidget());
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.SELECTNONETREE.getJsonWidgetTypeName(), new SelectNoneTreeWidget());
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.SELECTTREE.getJsonWidgetTypeName(), new SelectTreeWidget());
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.SELECTMULTI.getJsonWidgetTypeName(), new SelectMultiWidget());
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.RADIO.getJsonWidgetTypeName(), new RadioWidget());
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.CHECKBOX.getJsonWidgetTypeName(), new CheckboxWidget());
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.YEAR.getJsonWidgetTypeName(), new YearWidget());
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.MONTH.getJsonWidgetTypeName(), new MonthWidget());
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.DATE.getJsonWidgetTypeName(), new DateWidget());
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.LATLNG.getJsonWidgetTypeName(), new LatLngWidget());
        WIDGET_NAME_TO_WIDGET_CLASS.put(JsonWidgetType.IMAGEUPLOAD.getJsonWidgetTypeName(), new ImageUploadWidget());
    }


    @Override
    public PlainResult<Map<String, String>> getKeyValueInfo(Map<String, String> pureJsonTabInfoMap) {
        PlainResult<Map<String, String>> plainResult = new PlainResult<>();

        Map<String, String> result = new HashMap<>();

        if (CollectionUtils.isEmpty(pureJsonTabInfoMap)) {
            plainResult.setData(result);
            return plainResult;
        }

        try {
            Set<Map.Entry<String, String>> temp = pureJsonTabInfoMap.entrySet();
            for (Map.Entry<String, String> item : temp) {
                if (item.getKey().contains("_")) {
                    result.put(item.getKey().split("_")[1], item.getValue());
                }
            }
        } catch (Exception e) {
            plainResult.setError(CommonErrorCode.JSON_STR_PARSE_ERROR, e.toString());
            return plainResult;
        }
        plainResult.setData(result);
        return plainResult;
    }

    @Override
    public PlainResult<String> getPureJsonTabStr(UserTemplate userTemplate, String tabKey) {
        PlainResult<String> plainResult = new PlainResult<>();

        List<UserTemplateTab> userTemplateTabList = userTemplate.getUserTemplateTabList();

        boolean isTabKeyExist = false;
        for (UserTemplateTab item : userTemplateTabList) {
            if (tabKey.equals(item.getTabKey())) {
                isTabKeyExist = true;
                break;
            }
        }

        if (!isTabKeyExist) {
            return plainResult.setError(CommonErrorCode.TAB_KEY_NOT_EXIT, tabKey);
        }

        Map<String, UserTemplateInfo> userTemplateDataMap = userTemplate.getUserTemplateDataMap();

        if (!userTemplateDataMap.containsKey(tabKey)) {
            return plainResult.setError(CommonErrorCode.TAB_KEY_NOT_EXIT, tabKey);
        }

        String jsonValue = userTemplateDataMap.get(tabKey).getJsonValue();
        plainResult.setData(jsonValue);
        return plainResult;
    }

    @Override
    public Map<String, JSONObject> getWidgetsMap(String data) {
        Map<String, JSONObject> result = new HashMap<>();
        if (StringUtils.isEmpty(data)) {
            return result;
        }
        JSONArray jsonArray = JSONArray.parseArray(data);
        Object[] layouts = jsonArray.toArray();
        if (layouts.length == 0) {
            return result;
        }
        for (Object o : layouts) {
            JSONObject jsonObject = JSONObject.parseObject(o.toString());
            if ("layout".equals(jsonObject.get("type"))) {
                JSONObject bbb = JSONObject.parseObject(jsonObject.get("props").toString());
                JSONArray layoutListsJosnArray = JSONArray.parseArray(bbb.get("layoutLists").toString());
                Object[] layoutLists = layoutListsJosnArray.toArray();
                if (layoutLists.length == 0) {
                    return result;
                }
                for (Object oo : layoutLists) {
                    JSONObject ooJsonObject = JSONObject.parseObject(oo.toString());
                    JSONArray fieldListsJosnArray = JSONArray.parseArray(ooJsonObject.get("fieldLists").toString());
                    result.putAll(getWidgetsMap(fieldListsJosnArray.toString()));
                }
            } else {
                result.put(jsonObject.get("fieldNo").toString(), jsonObject);
            }
        }
        return result;
    }


    @Override
    public BaseResult checkPureJsonInfo(Map<String, JSONObject> widgetsMap, Map<String, String> dataMap) {
        BaseResult baseResult = new BaseResult();
        if (CollectionUtils.isEmpty(widgetsMap)) {
            return baseResult;
        }

        Set<Map.Entry<String, JSONObject>> widgetsEntrySet = widgetsMap.entrySet();

        String label = null;
        //todo
        try {
            for (Map.Entry<String, JSONObject> item : widgetsEntrySet) {
                String widgetName = item.getKey().split("_")[0];
                String keyName = item.getKey().split("_")[1];
                Object widgetClass = WIDGET_NAME_TO_WIDGET_CLASS.get(widgetName);
                if (widgetClass == null) {
                    return baseResult.setError(CommonErrorCode.WIDGET_NAME_IS_ILLEGAL, widgetName);
                }
                if (widgetClass instanceof InputWidget) {
                    InputWidget widget = JSON.parseObject(String.valueOf(item.getValue()), InputWidget.class);

                    InputProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (props.getRequired().equals(Boolean.TRUE)) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "");
                        }
                    }

                    if (props.getMaxlength() != null) {
                        if (dataMap.get(keyName).length() > props.getMaxlength()) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.WIDGET_EXCEED_MAX_LENGTH, label);
                        }
                    }

                } else if (widgetClass instanceof TextareaWidget) {
                    TextareaWidget widget = JSON.parseObject(String.valueOf(item.getValue()), TextareaWidget.class);

                    TextareaProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (props.getRequired().equals(Boolean.TRUE)) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "");
                        }
                    }

                    if (props.getMaxlength() != null) {
                        if (dataMap.get(keyName).length() > props.getMaxlength()) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.WIDGET_EXCEED_MAX_LENGTH, label);
                        }
                    }

                } else if (widgetClass instanceof SelectNoneTreeWidget) {
                    SelectNoneTreeWidget widget = JSON.parseObject(String.valueOf(item.getValue()), SelectNoneTreeWidget.class);
                    SelectNoneTreeProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (props.getRequired().equals(Boolean.TRUE)) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                        if (WidgetSourceType.PAGE_DEFINED.getWidgetSourceType().equals(props.getSourceType())) {
                            List<SelectNoneTreeOption> options = props.getOptions();
                            List<String> optionValue = new ArrayList<>();
                            for (SelectNoneTreeOption option : options) {
                                optionValue.add(option.getValue());
                            }
                            if (!optionValue.contains(dataMap.get(keyName))) {
                                label = props.getLabel();
                                return baseResult.setError(CommonErrorCode.WIDGET_VALUE_INVALID, label);
                            }
                        } else if (WidgetSourceType.SYSTEM_DEFINED.getWidgetSourceType().equals(props.getSourceType())) {
                            //todo 从数据源中进行筛选
                            if (StringUtils.isEmpty(props.getSourceName())) {
                                return baseResult.setError(CommonErrorCode.DATASOURCE_IS_ILLEGAL);
                            }
                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "");
                        }
                    }

                } else if (widgetClass instanceof SelectTreeWidget) {
                    SelectTreeWidget widget = JSON.parseObject(String.valueOf(item.getValue()), SelectTreeWidget.class);

                    SelectTreeProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (props.getRequired().equals(Boolean.TRUE)) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "");
                        }
                    }

                    if (!WidgetSourceType.SYSTEM_DEFINED.getWidgetSourceType().equals(props.getSourceType())) {
                        return baseResult.setError(CommonErrorCode.DATASOURCE_IS_ILLEGAL);
                    }

                    //todo 从数据源中进行筛选
                    if (StringUtils.isEmpty(props.getSourceName())) {
                        return baseResult.setError(CommonErrorCode.DATASOURCE_IS_ILLEGAL);
                    }

                } else if (widgetClass instanceof SelectMultiWidget) {
                    SelectMultiWidget widget = JSON.parseObject(String.valueOf(item.getValue()), SelectMultiWidget.class);

                    SelectMultiProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (props.getRequired().equals(Boolean.TRUE)) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName)) ||
                                "[]".equals(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                        if (WidgetSourceType.PAGE_DEFINED.getWidgetSourceType().equals(props.getSourceType())) {
                            List<SelectMultiOption> options = props.getOptions();
                            List<String> optionValue = new ArrayList<>();
                            for (SelectMultiOption option : options) {
                                optionValue.add(option.getValue());
                            }
                            if (!optionValue.contains(dataMap.get(keyName))) {
                                label = props.getLabel();
                                return baseResult.setError(CommonErrorCode.WIDGET_VALUE_INVALID, label);
                            }
                        } else if (WidgetSourceType.SYSTEM_DEFINED.getWidgetSourceType().equals(props.getSourceType())) {
                            //todo 从数据源中进行筛选
                            if (StringUtils.isEmpty(props.getSourceName())) {
                                return baseResult.setError(CommonErrorCode.DATASOURCE_IS_ILLEGAL);
                            }
                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "[]");
                        }
                    }
                } else if (widgetClass instanceof RadioWidget) {
                    RadioWidget widget = JSON.parseObject(String.valueOf(item.getValue()), RadioWidget.class);

                    RadioProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (Boolean.TRUE.equals(props.getRequired())) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                        List<RadioOption> options = props.getOptions();
                        List<String> optionValue = new ArrayList<>();
                        for (RadioOption option : options) {
                            optionValue.add(option.getValue());
                        }
                        if (!optionValue.contains(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.WIDGET_VALUE_INVALID, label);

                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "");
                        }
                    }
                } else if (widgetClass instanceof CheckboxWidget) {
                    CheckboxWidget widget = JSON.parseObject(String.valueOf(item.getValue()), CheckboxWidget.class);

                    CheckboxProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (props.getRequired().equals(Boolean.TRUE)) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName)) ||
                                "[]".equals(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                        List<CheckboxOption> options = props.getOptions();
                        List<String> optionValue = new ArrayList<>();
                        for (CheckboxOption option : options) {
                            optionValue.add(option.getValue());
                        }
                        if (!optionValue.contains(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.WIDGET_VALUE_INVALID, label);
                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "[]");
                        }
                    }
                } else if (widgetClass instanceof YearWidget) {
                    YearWidget widget = JSON.parseObject(String.valueOf(item.getValue()), YearWidget.class);

                    YearProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (props.getRequired().equals(Boolean.TRUE)) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "");
                        }
                    }

                } else if (widgetClass instanceof MonthWidget) {
                    MonthWidget widget = JSON.parseObject(String.valueOf(item.getValue()), MonthWidget.class);

                    MonthProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (props.getRequired().equals(Boolean.TRUE)) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "");
                        }
                    }

                } else if (widgetClass instanceof DateWidget) {
                    DateWidget widget = JSON.parseObject(String.valueOf(item.getValue()), DateWidget.class);

                    DateProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (props.getRequired().equals(Boolean.TRUE)) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "");
                        }
                    }

                } else if (widgetClass instanceof LatLngWidget) {
                    LatLngWidget widget = JSON.parseObject(String.valueOf(item.getValue()), LatLngWidget.class);

                    LatLngProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (props.getRequired().equals(Boolean.TRUE)) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName)) ||
                                "[]".equals(dataMap.get(keyName)) || dataMap.get(keyName).contains("NaN")) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "[]");
                        }
                    }

                } else if (widgetClass instanceof ImageUploadWidget) {
                    ImageUploadWidget widget = JSON.parseObject(String.valueOf(item.getValue()), ImageUploadWidget.class);

                    ImageUploadProps props = widget.getProps();
                    if (props == null) {
                        continue;
                    }

                    if (props.getRequired().equals(Boolean.TRUE)) {
                        if (!dataMap.containsKey(keyName) || StringUtils.isEmpty(dataMap.get(keyName)) ||
                                "[]".equals(dataMap.get(keyName))) {
                            label = props.getLabel();
                            return baseResult.setError(CommonErrorCode.DATA_NOT_MATCH_JSON, label);
                        }
                    } else {
                        if (!dataMap.containsKey(keyName)) {
                            dataMap.put(keyName, "[]");
                        }
                    }

                    List<String> widgetValue = (List<String>) JSON.parseObject(dataMap.get(keyName), List.class);

                    if (widgetValue.size() > props.getLimit()) {
                        label = props.getLabel();
                        return baseResult.setError(CommonErrorCode.WIDGET_EXCEED_MAX_LENGTH, label);
                    }

                } else {
                    return baseResult.setError(CommonErrorCode.JSON_WIDGET_NOT_FOUND);
                }
            }

        } catch (Exception e) {
            logger.error(String.format("widgetsMap: %s, dataMap: %s, Reason: %s, ExceptionStackTrace: %s",
                    widgetsMap.toString(), dataMap.toString(), "Failed to checkPureJsonInfo", e.toString()));
            baseResult.setError(CommonErrorCode.CHECK_ERROR, e.toString());
        }

        return baseResult;
    }

    @Override
    public PlainResult<List<String>> getTableTabStr(UserTemplate userTemplate, String tabKey) {
        PlainResult<List<String>> plainResult = new PlainResult<>();

        List<UserTemplateTab> userTemplateTabList = userTemplate.getUserTemplateTabList();

        boolean isTabKeyExist = false;
        for (UserTemplateTab item : userTemplateTabList) {
            if (tabKey.equals(item.getTabKey())) {
                isTabKeyExist = true;
                break;
            }
        }

        if (!isTabKeyExist) {
            return plainResult.setError(CommonErrorCode.NOT_EXIT, Param.TAB_KEY.getParam(), tabKey);
        }

        Map<String, UserTemplateInfo> sourceTemplateDataMap = userTemplate.getUserTemplateDataMap();

        if (!sourceTemplateDataMap.containsKey(tabKey)) {
            return plainResult.setError(CommonErrorCode.NOT_EXIT, Param.TAB_KEY.getParam(), tabKey);
        }

        List<String> tabKeyLabel = sourceTemplateDataMap.get(tabKey).getTabKeyLabelList();
        plainResult.setData(tabKeyLabel);
        return plainResult;
    }

    @Override
    public PlainResult<Map<String, String>> getTabKeyLabelMap(List<String> tabKeyLabel) {
        PlainResult<Map<String, String>> plainResult = new PlainResult<>();
        Map<String, String> tabKeyLabelMap = new HashMap<>();

        if (CollectionUtils.isEmpty(tabKeyLabel)) {
            plainResult.setData(tabKeyLabelMap);
            return plainResult;
        }

        for (String factorInfo : tabKeyLabel) {
            String[] tabKey = factorInfo.split(",");
            tabKeyLabelMap.put(tabKey[0], tabKey[1]);
        }

        plainResult.setData(tabKeyLabelMap);
        return plainResult;
    }

    @Override
    public BaseResult checkTableTabInfo(Map<String, JSONObject> widgetsMap, Map<String, String> keyLabelMap, Map<String, String> dataMap) {
        BaseResult baseResult = checkPureJsonInfo(widgetsMap, dataMap);
        if (!baseResult.isSuccess()) {
            return baseResult;
        }

        if (CollectionUtils.isEmpty(keyLabelMap) || CollectionUtils.isEmpty(widgetsMap)) {
            return baseResult;
        }

        Set<Map.Entry<String, JSONObject>> widgetsEntrySet = widgetsMap.entrySet();
        Set<String> keyNameSet = new HashSet<>();
        try {
            for (Map.Entry<String, JSONObject> item : widgetsEntrySet) {
                String keyName = item.getKey().split("_")[1];
                keyNameSet.add(keyName);
            }

            Set<Map.Entry<String, String>> keyLabelMapSet = keyLabelMap.entrySet();
            for (Map.Entry<String, String> item : keyLabelMapSet) {
                if (!keyNameSet.contains(item.getKey())) {
                    baseResult.setError(CommonErrorCode.JSON_DATA_IS_ILLEGAL);
                    return baseResult;
                }
            }
        } catch (Exception e) {
            logger.error(String.format("widgetsMap: %s, keyLabelMap: %s, dataMap: %s, Reason: %s, ExceptionStackTrace: %s",
                    widgetsMap.toString(), keyLabelMap.toString(), dataMap.toString(), "Failed to checkPureJsonInfo",
                    e.toString()));
        }

        return baseResult;
    }
}
