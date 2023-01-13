package cn.fishland.bookportal.service;

import cn.fishland.bookportal.bean.pojo.Ebook;

import java.util.List;

/**
 * 电子书服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface EbookService {

    List<Ebook> searchAll(String search);

}
