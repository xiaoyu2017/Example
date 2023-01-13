package cn.fishland.bookportal.bean.pojo;

import com.alibaba.fastjson2.JSON;

/**
 * 图片附件
 *
 * @author xiaoyu
 * @version 1.0
 */
public class ImageAttachment extends Attachment {
    private static final long serialVersionUID = -6555104700428586987L;

    public ImageAttachment() {
        this.type = 1;
    }

    @Override
    protected String toJson() {
        return JSON.toJSONString(this);
    }
}
