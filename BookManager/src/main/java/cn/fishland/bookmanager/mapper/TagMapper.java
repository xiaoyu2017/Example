package cn.fishland.bookmanager.mapper;

import cn.fishland.bookmanager.bean.pojo.AuthorTag;
import cn.fishland.bookmanager.bean.pojo.IsbnTag;
import cn.fishland.bookmanager.bean.pojo.PublisherTag;
import cn.fishland.bookmanager.bean.pojo.Tag;
import org.apache.ibatis.annotations.Param;

/**
 * 标签类Mapper
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface TagMapper {

    /**
     * 批量保存标签内容
     *
     * @param tags 标签实体类
     * @return int 影响行数
     */
    int insertPublisherTag(@Param("tags") PublisherTag... tags);

    int insertAuthorTag(@Param("tags") AuthorTag... tags);

    int insertIsbnTag(@Param("tags") IsbnTag... tags);

}
