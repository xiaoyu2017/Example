package cn.fishland.bookmanager.mapper;

import cn.fishland.bookmanager.bean.pojo.FileAttachment;
import cn.fishland.bookmanager.bean.pojo.ImageAttachment;
import org.apache.ibatis.annotations.Param;

/**
 * 附件Mapper类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface AttachmentMapper {

    int saveFileAttachment(@Param("attachments") FileAttachment... attachments);

    int saveImageAttachment(@Param("attachments") ImageAttachment... attachments);

    FileAttachment findFileAttachmentByEbookId(@Param("eid") int eid);

    ImageAttachment findImageAttachmentByEbookId(@Param("eid") int eid);
}
