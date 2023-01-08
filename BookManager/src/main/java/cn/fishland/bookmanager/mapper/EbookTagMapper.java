package cn.fishland.bookmanager.mapper;

import cn.fishland.bookmanager.bean.pojo.EbookTag;
import org.apache.ibatis.annotations.Param;

/**
 * ebook和tag的关联表
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface EbookTagMapper {

    int insertEbookTag(@Param("ebookTags") EbookTag... ebookTags);

}