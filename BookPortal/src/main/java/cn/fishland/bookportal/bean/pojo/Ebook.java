package cn.fishland.bookportal.bean.pojo;

import cn.fishland.bookportal.bean.BaseBean;
import com.alibaba.fastjson2.JSON;

import java.util.List;
import java.util.Objects;

/**
 * 电子图书类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class Ebook extends BaseBean {
    private static final long serialVersionUID = -3497357472846956625L;

    /** 书名 */
    private String bookName;
    /** 第几版 */
    private String edition;
    /** 发行年份 */
    private String year;
    /** 语言 */
    private String language;
    /** 页数 */
    private String pages;
    /** 是否带书签 */
    private Boolean bookmark = false;
    /** 简介 */
    private String summary;

    /** 出版社 */
    private PublisherTag publisher;
    /** 作者 */
    private AuthorTag author;
    /** ISBN */
    private List<IsbnTag> isbn;

    /** 封面 */
    private ImageAttachment image;
    /** 文件 */
    private FileAttachment file;


    /** 类别 */
    private List<Category> categories;

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

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
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
        return isbn;
    }

    public void setIsbn(List<IsbnTag> isbn) {
        this.isbn = isbn;
    }

    public ImageAttachment getImage() {
        return image;
    }

    public void setImage(ImageAttachment image) {
        this.image = image;
    }

    public FileAttachment getFile() {
        return file;
    }

    public void setFile(FileAttachment file) {
        this.file = file;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    @Override
    public String toString() {
        return "Ebook{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", sort=" + sort +
                ", bookName='" + bookName + '\'' +
                ", edition='" + edition + '\'' +
                ", year='" + year + '\'' +
                ", language='" + language + '\'' +
                ", pages='" + pages + '\'' +
                ", bookmark=" + bookmark +
                ", summary='" + summary + '\'' +
                ", publisher=" + publisher +
                ", author=" + author +
                ", isbn=" + isbn +
                ", image=" + image +
                ", file=" + file +
                ", categories=" + categories +
                "} " + super.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Ebook ebook = (Ebook) o;
        return Objects.equals(id, ebook.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this);
    }
}
