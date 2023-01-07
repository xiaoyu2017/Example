package cn.fishland.bookmanager.service;

import cn.fishland.bookmanager.bean.pojo.AuthorTag;
import cn.fishland.bookmanager.bean.pojo.IsbnTag;
import cn.fishland.bookmanager.bean.pojo.PublisherTag;

import java.util.List;

/**
 * 标签服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface TagService {

    List<PublisherTag> savePublisherTag(PublisherTag... tags);

    List<AuthorTag> saveAuthorTag(AuthorTag... tags);

    List<IsbnTag> saveIsbnTag(IsbnTag... tags);

}
