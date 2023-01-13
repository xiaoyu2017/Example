package cn.fishland.bookportal.bean.pojo;


import cn.fishland.bookportal.bean.BaseBean;
import com.alibaba.fastjson2.JSON;

import java.util.Objects;

/**
 * 书籍类别
 *
 * @author xiaoyu
 * @version 1.0
 */
public class Category extends BaseBean {
    private static final long serialVersionUID = -6285734675553452780L;

    public String name;

    public Category() {
    }

    public Category(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", sort=" + sort +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this);
    }
}
