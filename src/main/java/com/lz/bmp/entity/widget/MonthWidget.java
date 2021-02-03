package com.lz.bmp.entity.widget;

/**
 * 月控件类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class MonthWidget extends BaseWidget {
    private MonthProps props;

    public MonthProps getProps() {
        return props;
    }

    public void setProps(MonthProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "MonthWidget{" +
                "props=" + props +
                '}';
    }
}
