package cn.fishland.bookmanager.bean.pojo;

import cn.fishland.bookmanager.bean.BaseBean;
import com.alibaba.fastjson2.JSON;

import java.util.List;

/**
 * 电子图书类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class Ebook extends BaseBean {
    private static final long serialVersionUID = -6936712141044851069L;

    /** 书名 */
    private String bookName;
    /** 第几版 */
    private String edition;
    /** 发行年份 */
    private String year;
    /** 语言 */
    private String language;
    /** 页数 */
    private Integer pages;
    /** 是否带书签 */
    private Boolean bookmark;
    /** 简介 */
    private String summary;

    /** 出版社 */
    private PublisherTag publisher;
    /** 作者 */
    private AuthorTag author;
    /** ISBN */
    private List<IsbnTag> Isbn;

    /** 封面 */
    private Attachment image;
    /** 文件 */
    private Attachment file;

    /** 类别 */
    private List<Category> category;


    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Boolean getBookmark() {
        return bookmark;
    }

    public void setBookmark(Boolean bookmark) {
        this.bookmark = bookmark;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public PublisherTag getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherTag publisher) {
        this.publisher = publisher;
    }

    public AuthorTag getAuthor() {
        return author;
    }

    public void setAuthor(AuthorTag author) {
        this.author = author;
    }

    public List<IsbnTag> getIsbn() {
        return Isbn;
    }

    public void setIsbn(List<IsbnTag> isbn) {
        Isbn = isbn;
    }

    public Attachment getImage() {
        return image;
    }

    public void setImage(Attachment image) {
        this.image = image;
    }

    public Attachment getFile() {
        return file;
    }

    public void setFile(Attachment file) {
        this.file = file;
    }

    public List<Category> getCategory() {
        return category;
    }

    public void setCategory(List<Category> category) {
        this.category = category;
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this);
    }
}
