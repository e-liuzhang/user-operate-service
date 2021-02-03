package com.lz.bmp.entity.widget;

/**
 * 年控件类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class YearWidget extends BaseWidget {

    private YearProps props;

    public YearProps getProps() {
        return props;
    }

    public void setProps(YearProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "YearWidget{" +
                "props=" + props +
                '}';
    }
}
