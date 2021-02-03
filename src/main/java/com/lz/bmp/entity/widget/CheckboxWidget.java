package com.lz.bmp.entity.widget;

/**
 * 多选框控件类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class CheckboxWidget extends BaseWidget {

    private CheckboxProps props;

    public CheckboxProps getProps() {
        return props;
    }

    public void setProps(CheckboxProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "CheckboxWidget{" +
                "props=" + props +
                '}';
    }
}
