package cn.fishland.bookmanager.service;

import cn.fishland.bookmanager.bean.pojo.AuthorTag;
import cn.fishland.bookmanager.bean.pojo.IsbnTag;
import cn.fishland.bookmanager.bean.pojo.PublisherTag;

/**
 * 标签服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface TagService {

    boolean savePublisherTag(PublisherTag... tags);

    boolean saveAuthorTag(AuthorTag... tags);

    boolean saveIsbnTag(IsbnTag... tags);

}
