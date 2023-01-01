package cn.fishland.bookmanager.bean.pojo;

import cn.fishland.bookmanager.bean.BaseBean;

import java.util.List;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public class Ebook extends BaseBean {

    /** 书名 */
    private String bookName;
    /** 第几版 */
    private String edition;
    /** 出版社 */
    private String publisher;
    /** 作者 */
    private String author;
    /** ISBN */
    private List<String> Isbn;
    /** 发行年份 */
    private String year;
    /** 语言 */
    private String language;
    /** 大小 */
    private Double size;
    /** 大小单位 */
    private String sizeUnit;
    /** 页数 */
    private Integer pages;
    /** 是否带书签 */
    private Boolean bookmark;
    /** 格式 */
    private String extension;
    /** 简介 */
    private String summary;
    /** 封面 */
    private String icon;

    /** 文件 */
    private Attachment file;

    /** 类别 */
    private Integer category;


}
