package cn.fishland.bookmanager.bean.pojo;

import com.alibaba.fastjson2.JSON;

/**
 * 文件附件
 *
 * @author xiaoyu
 * @version 1.0
 */
public class FileAttachment extends Attachment {
    private static final long serialVersionUID = -5527208291093979361L;

    public FileAttachment() {
        this.type = 0;
    }

    @Override
    protected String toJson() {
        return JSON.toJSONString(this);
    }
}
