package com.lz.bmp.entity.widget;

/**
 * 多行文本框控件类
 *
 * @author shangang_luo
 * @date 20201031
 */
public class TextareaWidget extends BaseWidget {

    private TextareaProps props;

    public TextareaProps getProps() {
        return props;
    }

    public void setProps(TextareaProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "TextareaWidget{" +
                "props=" + props +
                '}';
    }
}
