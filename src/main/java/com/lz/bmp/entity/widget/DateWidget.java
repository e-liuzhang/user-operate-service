package com.lz.bmp.entity.widget;

/**
 * 日期控件类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class DateWidget extends BaseWidget {

    private DateProps props;

    public DateProps getProps() {
        return props;
    }

    public void setProps(DateProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "DateWidget{" +
                "props=" + props +
                '}';
    }
}
