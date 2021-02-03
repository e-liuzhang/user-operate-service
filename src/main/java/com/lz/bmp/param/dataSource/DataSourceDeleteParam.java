package com.lz.bmp.param.dataSource;


import java.util.List;

/**
 * @author rocky
 * @ClassName DataSourceDeleteParam
 * @Description 数据源删除
 * @Create by rocky
 * @Date 2020/10/29 上午11:41
 */
public class DataSourceDeleteParam {

    /**
     * ids 集合
     */
    private List<String> ids;

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "DataSourceDeleteParam{" +
                "ids=" + ids +
                '}';
    }
}
