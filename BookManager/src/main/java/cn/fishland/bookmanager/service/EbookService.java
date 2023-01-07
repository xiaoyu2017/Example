package cn.fishland.bookmanager.service;

import cn.fishland.bookmanager.bean.pojo.Ebook;

/**
 * 电子书服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface EbookService {

    boolean save(Ebook... ebooks);

}
