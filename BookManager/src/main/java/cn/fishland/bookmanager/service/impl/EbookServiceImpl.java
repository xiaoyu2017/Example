package cn.fishland.bookmanager.service.impl;

import cn.fishland.bookmanager.bean.pojo.*;
import cn.fishland.bookmanager.service.AttachmentService;
import cn.fishland.bookmanager.service.EbookService;
import cn.fishland.bookmanager.service.TagService;
import cn.fishland.bookmanager.tool.WebTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.List;

/**
 * 电子书服务类实现类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
public class EbookServiceImpl implements EbookService {

    @Override
    public boolean save(Ebook... ebooks) {
        if (ebooks.length > 0) {
            for (Ebook ebook : ebooks) {
                // 保存出版社
                TagService tagService = new TagServiceImpl();
                AttachmentService attachmentService = new AttachmentServiceImpl();

                if (!tagService.savePublisherTag(ebook.getPublisher())) {
                    log.error("ebook save Publisher error...");
                    return false;
                }

                // 保存作者
                if (!tagService.saveAuthorTag(ebook.getAuthor())) {
                    log.error("ebook save Author error...");
                    return false;
                }

                // 保存ISBN
                if (!tagService.saveIsbnTag(ebook.getIsbn().toArray(new IsbnTag[0]))) {
                    log.error("ebook save ISBN error...");
                    return false;
                }

                // 保存文件附件
                if (!attachmentService.saveFileAttachment(ebook.getFile())) {
                    log.error("ebook save File error...");
                    return false;
                }

                // 保存封面附件
                if (!attachmentService.saveImageAttachment(ebook.getImage())) {
                    log.error("ebook save Image error...");
                    return false;
                }

                // 保存图书
                int insert = WebTool.ebookMapper.insertAll(ebooks);
                if (insert != ebooks.length) {
                    log.error("ebook save error...");
                    return false;
                }

                // 出版社中间表
                EbookTag ebookTagPublisher = new EbookTag(ebook.getId(), ebook.getPublisher().getId(), ebook.getPublisher().getType());
                // 作者中间表
                EbookTag ebookTagAuthor = new EbookTag(ebook.getId(), ebook.getAuthor().getId(), ebook.getAuthor().getType());
                // ISBN中间表
                List<EbookTag> ebookTagIsbn = new ArrayList<>();
                for (IsbnTag isbnTag : ebook.getIsbn()) {
                    ebookTagIsbn.add(new EbookTag(ebook.getId(), isbnTag.getId(), isbnTag.getType()));
                }

                // 保存Tag中间表
                int insertEbookTagPublisher = WebTool.ebookTagMapper.insertEbookTag(ebookTagPublisher);
                if (insertEbookTagPublisher <= 0) {
                    log.error("ebook save EbookTagPublisher error...");
                    return false;
                }
                int insertEbookTagAuthor = WebTool.ebookTagMapper.insertEbookTag(ebookTagAuthor);
                if (insertEbookTagAuthor <= 0) {
                    log.error("ebook save EbookTagAuthor error...");
                    return false;
                }
                int insertEbookTagIsbn = WebTool.ebookTagMapper.insertEbookTag(ebookTagIsbn.toArray(new EbookTag[0]));
                if (insertEbookTagIsbn <= 0) {
                    log.error("ebook save EbookTagIsbn error...");
                    return false;
                }

                // 保存类别
                List<Category> categories = ebook.getCategory();
                if (categories != null && categories.size() > 0) {
                    // 因为类别mapper没用代理接口，所以只能直接使用sqlSession
                    SqlSession sqlSession = WebTool.sqlSession();

                    // 获得数据库中已存在的类别
                    List<Category> list = sqlSession.selectList("categoryMapper.selectByName", categories.toArray(new Category[0]));

                    // 取当前类别和数据库中的差集
                    categories.removeAll(list);

                    // 直接保存类别，忽略操作行数，可能存在数据库中存在的类别
                    if (categories.size() > 0) {
                        int insertCategories = sqlSession.insert("categoryMapper.save", categories.toArray(new Category[0]));
                        sqlSession.commit();
                        if (insertCategories != categories.size()) {
                            log.info("save ebook category error");
                            return false;
                        }
                    }

                    // 合并集合
                    categories.addAll(list);

                    // 保存类别中间表
                    List<EbookCategory> ebookCategoryList = new ArrayList<>();
                    for (Category category : categories) {
                        ebookCategoryList.add(new EbookCategory(ebook.getId(), category.getId()));
                    }
                    int insertEbookCategory = WebTool.ebookCategoryMapper.insertEbookCategory(ebookCategoryList.toArray(new EbookCategory[0]));
                    if (insertEbookCategory != ebookCategoryList.size()) {
                        log.error("save ebook EbookCategory error...");
                        return false;
                    }
                }
            }

            log.debug("save ebook success");
            return true;
        }
        log.debug("PublisherTags is empty");
        return false;
    }
}
