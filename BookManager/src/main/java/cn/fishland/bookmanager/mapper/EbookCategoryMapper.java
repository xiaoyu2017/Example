package cn.fishland.bookmanager.mapper;

import cn.fishland.bookmanager.bean.pojo.EbookCategory;
import org.apache.ibatis.annotations.Param;

/**
 * ebook和category中间表
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface EbookCategoryMapper {

    int insertEbookCategory(@Param("ebookCategories") EbookCategory... ebookCategories);

    int deleteAll(@Param("ids") int... ids);
}
