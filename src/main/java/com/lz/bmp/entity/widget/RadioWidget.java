package com.lz.bmp.entity.widget;

/**
 * 单选框控件类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class RadioWidget extends BaseWidget {

    private RadioProps props;

    public RadioProps getProps() {
        return props;
    }

    public void setProps(RadioProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "RadioWidget{" +
                "props=" + props +
                '}';
    }
}
