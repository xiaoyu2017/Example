package cn.fishland.bookmanager.service.impl;

import cn.fishland.bookmanager.bean.pojo.AuthorTag;
import cn.fishland.bookmanager.bean.pojo.IsbnTag;
import cn.fishland.bookmanager.bean.pojo.PublisherTag;
import cn.fishland.bookmanager.service.TagService;
import cn.fishland.bookmanager.tool.WebTool;
import lombok.extern.slf4j.Slf4j;

/**
 * 标签服务类实现类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
public class TagServiceImpl implements TagService {

    @Override
    public boolean savePublisherTag(PublisherTag... tags) {
        if (tags.length > 0) {
            int insert = WebTool.tagMapper.insertPublisherTag(tags);
            log.debug(String.format("insert into PublisherTags num=[%s]", insert));
            if (insert == tags.length) {
                return true;
            }
        }
        log.debug("PublisherTags is empty");
        return false;
    }

    @Override
    public boolean saveAuthorTag(AuthorTag... tags) {
        if (tags.length > 0) {
            int insert = WebTool.tagMapper.insertAuthorTag(tags);
            log.debug(String.format("insert into PublisherTags num=[%s]", insert));
            if (insert == tags.length) {
                return true;
            }
        }
        log.debug("PublisherTags is empty");
        return false;
    }

    @Override
    public boolean saveIsbnTag(IsbnTag... tags) {
        if (tags.length > 0) {
            int insert = WebTool.tagMapper.insertIsbnTag(tags);
            log.debug(String.format("insert into PublisherTags num=[%s]", insert));
            if (insert == tags.length) {
                return true;
            }
        }
        log.debug("PublisherTags is empty");
        return false;
    }

}
