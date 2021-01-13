package com.lz.bmp.entity.userTemplate;

/**
 * @Author shangang_luo
 * @Date 2021/1/13 21:31
 */

public class UserTemplateTab {

    /**
     * tab页的唯一识别码
     */
    private String tabKey;

    /**
     * tab页的显示名称
     */
    private String tabLabel;

    /**
     * tab页的渲染方式
     */
    private Integer tabType;

    /**
     * 是否是基本类型
     */
    private Integer isBasicInfo;

    public String getTabKey() {
        return tabKey;
    }

    public void setTabKey(String tabKey) {
        this.tabKey = tabKey;
    }

    public String getTabLabel() {
        return tabLabel;
    }

    public void setTabLabel(String tabLabel) {
        this.tabLabel = tabLabel;
    }

    public Integer getTabType() {
        return tabType;
    }

    public void setTabType(Integer tabType) {
        this.tabType = tabType;
    }

    public Integer getIsBasicInfo() {
        return isBasicInfo;
    }

    public void setIsBasicInfo(Integer isBasicInfo) {
        this.isBasicInfo = isBasicInfo;
    }

    @Override
    public String toString() {
        return "UserTemplateTab{" +
                "tabKey='" + tabKey + '\'' +
                ", tabLabel='" + tabLabel + '\'' +
                ", tabType=" + tabType +
                ", isBasicInfo=" + isBasicInfo +
                '}';
    }
}
