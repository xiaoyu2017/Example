package cn.fishland.bookportal.bean.pojo;

import com.alibaba.fastjson2.JSON;

/**
 * 出版社标签类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class PublisherTag extends Tag {
    private static final long serialVersionUID = 5896975505207424026L;

    public PublisherTag() {
        this.type = 1;
    }

    public PublisherTag(String name) {
        this.name = name;
        this.type = 1;
    }

    @Override
    public String toString() {
        return "PublisherTag{" +
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
    protected String toJson() {
        return JSON.toJSONString(this);
    }

}
