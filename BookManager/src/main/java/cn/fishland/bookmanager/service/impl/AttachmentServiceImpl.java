package cn.fishland.bookmanager.service.impl;

import cn.fishland.bookmanager.bean.pojo.FileAttachment;
import cn.fishland.bookmanager.bean.pojo.ImageAttachment;
import cn.fishland.bookmanager.service.AttachmentService;
import cn.fishland.bookmanager.tool.WebTool;
import lombok.extern.slf4j.Slf4j;

/**
 * 附件服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {

    @Override
    public boolean saveFileAttachment(FileAttachment... attachments) {
        if (attachments.length > 0) {
            int insert = WebTool.attachmentMapper.saveFileAttachment(attachments);
            log.debug(String.format("insert into FileAttachment num=[%s]", insert));
            return true;
        }
        log.debug("FileAttachment is empty");
        return false;
    }

    @Override
    public boolean saveImageAttachment(ImageAttachment... attachments) {
        if (attachments.length > 0) {
            int insert = WebTool.attachmentMapper.saveImageAttachment(attachments);
            log.debug(String.format("insert into FileAttachment num=[%s]", insert));
            return true;
        }
        log.debug("FileAttachment is empty");
        return false;
    }
}
