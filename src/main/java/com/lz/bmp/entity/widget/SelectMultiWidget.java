package com.lz.bmp.entity.widget;

/**
 * 下拉多选框控件类
 *
 * @author shangang_luo
 * @date 20201031
 */
public class SelectMultiWidget extends BaseWidget {

    private SelectMultiProps props;

    public SelectMultiProps getProps() {
        return props;
    }

    public void setProps(SelectMultiProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "Select3Widget{" +
                "props=" + props +
                '}';
    }
}
