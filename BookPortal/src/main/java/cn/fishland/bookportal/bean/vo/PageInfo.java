package cn.fishland.bookportal.bean.vo;

import java.io.Serializable;
import java.util.List;

/**
 * 分页信息类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class PageInfo<T> implements Serializable {
    private static final long serialVersionUID = 5387281859778660507L;

    /** 数据 */
    private List<T> list;
    /** 当前页码 */
    private Integer pageNum;
    /** 每页显示数 */
    private Integer pageSize;
    /** 当前页真实数 */
    private Integer size;
    /** 总数 */
    private Integer total;
    /** 总页数 */
    private Integer pages;
    /** 上一页页码 */
    private Integer prePage;
    /** 下一页页码 */
    private Integer nextPage;

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Integer getPrePage() {
        return prePage;
    }

    public void setPrePage(Integer prePage) {
        this.prePage = prePage;
    }

    public Integer getNextPage() {
        return nextPage;
    }

    public void setNextPage(Integer nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public String toString() {
        return "PageInfo{" +
                "list=" + list +
                ", pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", size=" + size +
                ", total=" + total +
                ", pages=" + pages +
                ", prePage=" + prePage +
                ", nextPage=" + nextPage +
                '}';
    }
}
