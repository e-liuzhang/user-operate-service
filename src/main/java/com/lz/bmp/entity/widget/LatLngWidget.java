package com.lz.bmp.entity.widget;

/**
 * 经纬度控件类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class LatLngWidget extends BaseWidget {

    private LatLngProps props;

    public LatLngProps getProps() {
        return props;
    }

    public void setProps(LatLngProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "LatLngWidget{" +
                "props=" + props +
                '}';
    }
}
