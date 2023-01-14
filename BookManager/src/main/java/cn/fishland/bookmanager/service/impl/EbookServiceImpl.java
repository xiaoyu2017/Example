package cn.fishland.bookmanager.service.impl;

import cn.fishland.bookmanager.bean.pojo.*;
import cn.fishland.bookmanager.bean.vo.EbookVo;
import cn.fishland.bookmanager.service.AttachmentService;
import cn.fishland.bookmanager.service.EbookService;
import cn.fishland.bookmanager.service.TagService;
import cn.fishland.bookmanager.tool.WebTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.ArrayList;
import java.util.Arrays;
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
                AttachmentService attachmentService = new AttachmentServiceImpl();
                TagService tagService = new TagServiceImpl();

                // 保存出版社
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

                // 保存图书
                int insert = WebTool.ebookMapper.insertAll(ebooks);
                if (insert != ebooks.length) {
                    log.error("ebook save error...");
                    return false;
                }

                // 保存文件附件
                FileAttachment file = ebook.getFile();
                if (file == null) {
                    log.error("save ebook file attachment empty");
                    return false;
                }
                ebook.getFile().setParent(ebook.getId());
                if (!attachmentService.saveFileAttachment(file)) {
                    log.error("ebook save File error...");
                    return false;
                }

                // 保存封面附件
                ImageAttachment image = ebook.getImage();
                if (image != null) {
                    ebook.getImage().setParent(ebook.getId());
                    if (!attachmentService.saveImageAttachment(image)) {
                        log.error("ebook save Image error...");
                        return false;
                    }
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
                List<Category> categories = ebook.getCategories();
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

    @Override
    public PageInfo<Ebook> findAll(int page, int num, EbookVo ebookVo) {
        PageHelper.startPage(page, num);
        List<Ebook> ebooks = WebTool.ebookMapper.findAll(ebookVo);
        return new PageInfo<>(ebooks, 5);
    }

    @Override
    public boolean remove(int... ids) {
        // 删除Ebook
        int deleteAll = WebTool.ebookMapper.deleteAll(ids);
        if (deleteAll != ids.length) {
            log.error(String.format("delete ebook error ids={%s}", Arrays.toString(ids)));
            return false;
        }

        // 删除EbookTag
        deleteAll = WebTool.ebookTagMapper.deleteAll(ids);
        log.info(String.format("delete ebook-tag number={%s} ids={%s}", deleteAll, Arrays.toString(ids)));

        // 删除EbookCategory
        deleteAll = WebTool.ebookCategoryMapper.deleteAll(ids);
        log.info(String.format("delete ebook-category number={%s} ids={%s}", deleteAll, Arrays.toString(ids)));

        // 删除Ebook-Attachment
        List<Attachment> attachments = WebTool.attachmentMapper.findAllByEid(ids);
        if (attachments != null && attachments.size() > 0) {
            // 1. 删除物理文件
            attachments.forEach(a -> {
                String filePath = a.getFilePath();
                if (!WebTool.isBlank(filePath)) {
                    WebTool.deleteFile(filePath);
                }
            });

            // 2. 删除数据库文件
            deleteAll = WebTool.attachmentMapper.deleteAll(ids);
            log.info(String.format("delete ebook-attachment number={%s} ids={%s}", deleteAll, Arrays.toString(ids)));
        }
        return true;
    }

    @Override
    public Ebook getById(int id) {
        return WebTool.ebookMapper.selectById(id);
    }

}
