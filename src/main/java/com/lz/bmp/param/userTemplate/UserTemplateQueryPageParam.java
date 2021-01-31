package com.lz.bmp.param.userTemplate;

/**
 * @Author shangang_luo
 * @Date 2021/1/31 20:02
 */

public class UserTemplateQueryPageParam {

    private String name;

    private Integer size;

    private Integer page;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public String toString() {
        return "UserTemplateQueryPageParam{" +
                "name='" + name + '\'' +
                ", size=" + size +
                ", page=" + page +
                '}';
    }
}
