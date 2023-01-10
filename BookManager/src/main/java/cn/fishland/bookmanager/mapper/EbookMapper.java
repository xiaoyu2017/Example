package cn.fishland.bookmanager.mapper;

import cn.fishland.bookmanager.bean.pojo.Ebook;
import cn.fishland.bookmanager.bean.vo.EbookVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 电子书Mapper类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface EbookMapper {

    int insertAll(@Param("ebooks") Ebook... ebooks);

    List<Ebook> findAll(EbookVo ebookVo);

    int deleteAll(@Param("ids") int... ids);

    Ebook selectById(@Param("id") int id);
}
