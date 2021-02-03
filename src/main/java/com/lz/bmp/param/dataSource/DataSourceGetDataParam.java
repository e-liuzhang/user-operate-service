package com.lz.bmp.param.dataSource;

import java.util.List;

/**
 * @author rocky
 * @ClassName DataSourceGetData
 * @Description  数据源
 * @Create by rocky
 * @Date 2020/10/29 下午3:09
 */
public class DataSourceGetDataParam {

    private List<String> dataSourceCodes;

    public List<String> getDataSourceCodes() {
        return dataSourceCodes;
    }

    public void setDataSourceCodes(List<String> dataSourceCodes) {
        this.dataSourceCodes = dataSourceCodes;
    }

    @Override
    public String toString() {
        return "DataSourceGetDataParam{" +
                "dataSourceCodes=" + dataSourceCodes +
                '}';
    }
}
