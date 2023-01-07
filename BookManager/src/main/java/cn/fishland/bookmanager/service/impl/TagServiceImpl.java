package cn.fishland.bookmanager.service.impl;

import cn.fishland.bookmanager.bean.pojo.AuthorTag;
import cn.fishland.bookmanager.bean.pojo.IsbnTag;
import cn.fishland.bookmanager.bean.pojo.PublisherTag;
import cn.fishland.bookmanager.mapper.TagMapper;
import cn.fishland.bookmanager.service.TagService;
import cn.fishland.bookmanager.tool.WebTool;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * 标签服务类实现类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
public class TagServiceImpl implements TagService {

    private TagMapper tagMapper;

    {
        try {
            tagMapper = (TagMapper) WebTool.getMapper(TagMapper.class);
            log.debug("init TagMapper class...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<PublisherTag> savePublisherTag(PublisherTag... tags) {
        if (tags.length > 0) {
            int insert = tagMapper.insertPublisherTag(tags);
            log.debug(String.format("insert into PublisherTags num=[%s]", insert));
            return Arrays.asList(tags);
        }
        log.debug("PublisherTags is empty");
        return null;
    }

    @Override
    public List<AuthorTag> saveAuthorTag(AuthorTag... tags) {
        if (tags.length > 0) {
            int insert = tagMapper.insertAuthorTag(tags);
            log.debug(String.format("insert into PublisherTags num=[%s]", insert));
            return Arrays.asList(tags);
        }
        log.debug("PublisherTags is empty");
        return null;
    }

    @Override
    public List<IsbnTag> saveIsbnTag(IsbnTag... tags) {
        if (tags.length > 0) {
            int insert = tagMapper.insertIsbnTag(tags);
            log.debug(String.format("insert into PublisherTags num=[%s]", insert));
            return Arrays.asList(tags);
        }
        log.debug("PublisherTags is empty");
        return null;
    }

}
