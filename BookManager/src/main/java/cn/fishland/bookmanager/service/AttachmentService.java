package cn.fishland.bookmanager.service;

import cn.fishland.bookmanager.bean.pojo.FileAttachment;
import cn.fishland.bookmanager.bean.pojo.ImageAttachment;

/**
 * 附件服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface AttachmentService {

    boolean saveFileAttachment(FileAttachment... attachments);

    boolean saveImageAttachment(ImageAttachment... attachments);

}
