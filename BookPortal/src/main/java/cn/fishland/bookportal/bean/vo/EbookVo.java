package cn.fishland.bookportal.bean.vo;

import cn.fishland.bookportal.bean.BaseBean;

import java.util.Objects;

/**
 * 显示Ebook类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class EbookVo extends BaseBean {
    private static final long serialVersionUID = 4825566185542057418L;

    private String bookName;
    private String author;
    private String publisher;
    private String year;
    private String language;
    private String size;
    private String sizeUnit;
    private String extension;
    private Long imageId;
    private String info;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getSizeUnit() {
        return sizeUnit;
    }

    public void setSizeUnit(String sizeUnit) {
        this.sizeUnit = sizeUnit;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EbookVo that = (EbookVo) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "EbookVo{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", sort=" + sort +
                ", bookName='" + bookName + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", year='" + year + '\'' +
                ", language='" + language + '\'' +
                ", size='" + size + '\'' +
                ", sizeUnit='" + sizeUnit + '\'' +
                ", extension='" + extension + '\'' +
                ", info='" + info + '\'' +
                "} " + super.toString();
    }

    @Override
    protected String toJson() {
        return null;
    }
}
