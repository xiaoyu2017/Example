package cn.fishland.bookmanager.mapper;

import cn.fishland.bookmanager.bean.pojo.Attachment;
import cn.fishland.bookmanager.bean.pojo.FileAttachment;
import cn.fishland.bookmanager.bean.pojo.ImageAttachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    int deleteAll(@Param("ids") int... ids);

    List<Attachment> findAllByEid(@Param("ids") int... ids);

}
