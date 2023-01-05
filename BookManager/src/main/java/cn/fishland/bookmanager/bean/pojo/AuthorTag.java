package cn.fishland.bookmanager.bean.pojo;

/**
 * 原书作者标签类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class AuthorTag extends Tag {
    private static final long serialVersionUID = -6037317514913620773L;

    public AuthorTag() {
        this.type = 2;
    }

    public AuthorTag(String name) {
        this.type = 2;
        this.name = name;
    }

    @Override
    public String toString() {
        return "AuthorTag{" +
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
        return null;
    }
}
