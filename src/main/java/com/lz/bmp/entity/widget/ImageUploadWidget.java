package com.lz.bmp.entity.widget;

/**
 * 图片上传控件类
 *
 * @author shangang_luo
 * @date 20201101
 */
public class ImageUploadWidget extends BaseWidget {

    private ImageUploadProps props;

    public ImageUploadProps getProps() {
        return props;
    }

    public void setProps(ImageUploadProps props) {
        this.props = props;
    }

    @Override
    public String toString() {
        return "ImageUploadWidget{" +
                "props=" + props +
                '}';
    }
}
