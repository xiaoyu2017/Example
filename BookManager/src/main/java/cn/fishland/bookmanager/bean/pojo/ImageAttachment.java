package cn.fishland.bookmanager.bean.pojo;

import com.alibaba.fastjson2.JSON;

/**
 * 图片附件
 *
 * @author xiaoyu
 * @version 1.0
 */
public class ImageAttachment extends Attachment {
    private static final long serialVersionUID = 1779736374891151578L;

    public ImageAttachment() {
        this.type = 1;
    }

    @Override
    protected String toJson() {
        return JSON.toJSONString(this);
    }
}
