package com.lz.bmp.utils;

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
     * 拼接路径查询 .
     */
    public static final String POINT = ".";
}
