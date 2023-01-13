package cn.fishland.bookportal.bean.pojo;

import com.alibaba.fastjson2.JSON;

/**
 * ISBN标签类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class IsbnTag extends Tag {
    private static final long serialVersionUID = -610811190053749903L;

    public IsbnTag() {
        this.type = 0;
    }

    public IsbnTag(String name) {
        this.name = name;
        this.type = 0;
    }

    @Override
    public String toString() {
        return "IsbnTag{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", sort=" + sort +
                ", type=" + type +
                ", name='" + name + '\'' +
                "} " + super.toString();
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this);
    }
}
