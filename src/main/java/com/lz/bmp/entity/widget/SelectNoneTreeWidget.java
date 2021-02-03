package com.lz.bmp.entity.widget;

/**
 * 下拉单选框(非树结构)控件类
 *
 * @author shangang_luo
 * @date 20201031
 */
public class SelectNoneTreeWidget extends BaseWidget {

    private SelectNoneTreeProps props;

    public SelectNoneTreeProps getProps() {
        return props;
    }

    public void setProps(SelectNoneTreeProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "Select1Widget{" +
                "props=" + props +
                '}';
    }
}
