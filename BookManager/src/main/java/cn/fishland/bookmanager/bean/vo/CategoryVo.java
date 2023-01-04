package cn.fishland.bookmanager.bean.vo;

import cn.fishland.bookmanager.bean.BaseBean;
import com.alibaba.fastjson2.JSON;

import java.util.Date;

/**
 * 类别VO
 *
 * @author xiaoyu
 * @version 1.0
 */
public class CategoryVo extends BaseBean {

    private static final long serialVersionUID = -4228683243929185466L;

    private String name;
    private Date createStart;
    private Date createEnd;
    private Date updateStart;
    private Date updateEnd;

    public CategoryVo() {
    }

    public CategoryVo(String name, Date createStart, Date createEnd, Date updateStart, Date updateEnd) {
        this.name = name;
        this.createStart = createStart;
        this.createEnd = createEnd;
        this.updateStart = updateStart;
        this.updateEnd = updateEnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateStart() {
        return createStart;
    }

    public void setCreateStart(Date createStart) {
        this.createStart = createStart;
    }

    public Date getCreateEnd() {
        return createEnd;
    }

    public void setCreateEnd(Date createEnd) {
        this.createEnd = createEnd;
    }

    public Date getUpdateStart() {
        return updateStart;
    }

    public void setUpdateStart(Date updateStart) {
        this.updateStart = updateStart;
    }

    public Date getUpdateEnd() {
        return updateEnd;
    }

    public void setUpdateEnd(Date updateEnd) {
        this.updateEnd = updateEnd;
    }

    @Override
    public String toString() {
        return "CategoryVo{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", sort=" + sort +
                ", name='" + name + '\'' +
                ", createStart=" + createStart +
                ", createEnd=" + createEnd +
                ", updateStart=" + updateStart +
                ", updateEnd=" + updateEnd +
                "} " + super.toString();
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this);
    }
}
