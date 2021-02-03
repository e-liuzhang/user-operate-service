package com.lz.bmp.entity.widget;

/**
 * 输入框控件类
 *
 * @author shangang_luo
 *
 */
public class InputWidget extends BaseWidget {

    private InputProps props;

    public InputProps getProps() {
        return props;
    }

    public void setProps(InputProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "InputWidget{" +
                "props=" + props +
                '}';
    }
}
