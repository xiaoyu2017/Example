package cn.fishland.bookmanager.mapper;

import cn.fishland.bookmanager.bean.pojo.Ebook;
import org.apache.ibatis.annotations.Param;

/**
 * 电子书Mapper类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface EbookMapper {

    int insertAll(@Param("ebooks") Ebook... ebooks);

}
