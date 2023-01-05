package cn.fishland.bookmanager.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有Bean的基类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Data
public abstract class BaseBean implements Serializable {

    private static final long serialVersionUID = -2282893766014331881L;

    protected Long id;

    protected Date createTime;

    protected Date updateTime;

    protected Boolean status = true;

    protected Integer sort = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    protected abstract String toJson();
}
