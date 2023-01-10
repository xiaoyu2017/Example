package cn.fishland.bookmanager.service;

import cn.fishland.bookmanager.bean.pojo.Ebook;
import cn.fishland.bookmanager.bean.vo.EbookVo;
import com.github.pagehelper.PageInfo;

/**
 * 电子书服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface EbookService {

    boolean save(Ebook... ebooks);

    /**
     * 获得所有电子书信息
     *
     * @param ebookVo 查询条件
     * @param page    查询的页数
     * @param num     每页个数
     * @return 符合条件的电子书集合
     */
    PageInfo<Ebook> findAll(int page, int num, EbookVo ebookVo);

    boolean remove(int... ids);

    Ebook getById(int id);
}
