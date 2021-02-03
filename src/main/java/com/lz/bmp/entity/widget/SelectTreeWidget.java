package com.lz.bmp.entity.widget;

/**
 * 下拉单选框(树结构)控件类
 *
 * @author shangang_luo
 * @date 20201031
 */
public class SelectTreeWidget extends BaseWidget {

    private SelectTreeProps props;

    public SelectTreeProps getProps() {
        return props;
    }

    public void setProps(SelectTreeProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "Select2Widget{" +
                "props=" + props +
                '}';
    }
}
