package com.lz.bmp.utils;

import com.alibaba.fastjson.JSONObject;
import com.fpi.simple.result.PlainResult;
import com.lz.bmp.dataenum.CommonErrorCode;
import com.lz.bmp.dataenum.DataSourceSymbol;
import org.springframework.util.StringUtils;

import java.util.UUID;

/**
 * @Author shangang_luo
 * @Date 2021/1/21 20:16
 */

public class CommonUtils {


    /**
     * 获取UUID
     *
     * @return
     */
    public static String getUuid() {
        UUID uuid = UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }

    /**
     * 模糊查询时替换
     *
     * @param str
     * @return
     */
    public static String regexPreHandle(String str) {
        return str.replace("\\", "\\\\").replace("*", "\\*")
                .replace("+", "\\+").replace("|", "\\|")
                .replace("{", "\\{").replace("}", "\\}")
                .replace("(", "\\(").replace(")", "\\)")
                .replace("^", "\\^").replace("$", "\\$")
                .replace("[", "\\[").replace("]", "\\]")
                .replace("?", "\\?").replace(",", "\\,")
                .replace(".", "\\.").replace("&", "\\&");
    }

    /**
     * &pn=50&oq=get做路参&tn=baiduhome_pg&
     *
     * @param parameter
     * @return
     */
    public static PlainResult<JSONObject> getJSONObject(String parameter) {
        PlainResult<JSONObject> plainResult = new PlainResult();
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(parameter)) {
            plainResult.setData(new JSONObject());
            return plainResult;
        }
        String[] str = parameter.split(DataSourceSymbol.SYMBOL_AND.getName());
        for (String s : str) {
            int index = s.indexOf(DataSourceSymbol.EQUAL.getName());
            if (index == -1) {
                return plainResult.setError(CommonErrorCode.DATASOURCE_PARAMETER_IS_NOT_LEGAL);
            }
            String str1 = s.substring(0, index);
            String str2 = s.substring(index + 1);
            jsonObject.put(str1, str2);
        }
        plainResult.setData(jsonObject);
        return plainResult;
    }

    /**
     * restTremplate get 专用
     * pn&oq&tn
     *
     * @param param
     * @return 返回请求的参数
     */
    public static String getRequestParam(String param) {
        if (StringUtils.isEmpty(param)) {
            return "";
        }
        String[] split = param.split(DataSourceSymbol.SYMBOL_AND.getName());
        StringBuilder requestParam = new StringBuilder();
        if (split.length > 0) {
            requestParam.append(DataSourceSymbol.ASK_SYMBOL.getName());
            for (String s : split) {
                int i = s.indexOf(DataSourceSymbol.EQUAL.getName());
                String splitRes = s.substring(0, i);
                requestParam.append(String.format(DataSourceSymbol.PARAM_TRANSFORM.getName(), splitRes, splitRes));
            }
        }
        return requestParam.toString();
    }


    /**
     * 拼接路径查询 .
     */
    public static final String POINT = ".";
}
