package cn.fishland.bookmanager.service.impl;

import cn.fishland.bookmanager.bean.pojo.FileAttachment;
import cn.fishland.bookmanager.bean.pojo.ImageAttachment;
import cn.fishland.bookmanager.mapper.AttachmentMapper;
import cn.fishland.bookmanager.service.AttachmentService;
import cn.fishland.bookmanager.tool.WebTool;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 附件服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
public class AttachmentServiceImpl implements AttachmentService {

    private AttachmentMapper attachmentMapper;

    {
        try {
            attachmentMapper = (AttachmentMapper) WebTool.getMapper(AttachmentMapper.class);
            log.debug("init TagMapper class...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean saveFileAttachment(FileAttachment... attachments) {
        if (attachments.length > 0) {
            int insert = attachmentMapper.saveFileAttachment(attachments);
            log.debug(String.format("insert into FileAttachment num=[%s]", insert));
            return true;
        }
        log.debug("FileAttachment is empty");
        return false;
    }

    @Override
    public boolean saveImageAttachment(ImageAttachment... attachments) {
        if (attachments.length > 0) {
            int insert = attachmentMapper.saveImageAttachment(attachments);
            log.debug(String.format("insert into FileAttachment num=[%s]", insert));
            return true;
        }
        log.debug("FileAttachment is empty");
        return false;
    }
}
