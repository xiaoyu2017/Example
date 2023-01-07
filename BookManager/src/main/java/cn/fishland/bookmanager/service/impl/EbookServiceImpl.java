package cn.fishland.bookmanager.service.impl;

import cn.fishland.bookmanager.bean.pojo.Ebook;
import cn.fishland.bookmanager.mapper.EbookMapper;
import cn.fishland.bookmanager.service.EbookService;
import cn.fishland.bookmanager.tool.WebTool;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 电子书服务类实现类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
public class EbookServiceImpl implements EbookService {

    private EbookMapper ebookMapper;

    {
        try {
            ebookMapper = (EbookMapper) WebTool.getMapper(EbookMapper.class);
            log.debug("init EbookMapper class...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean save(Ebook... ebooks) {
        if (ebooks.length > 0) {
            int insert = ebookMapper.insertAll(ebooks);
            log.debug(String.format("insert into PublisherTags num=[%s]", insert));
            if (insert == ebooks.length) {
                return true;
            }
            return false;
        }
        log.debug("PublisherTags is empty");
        return false;
    }
}
