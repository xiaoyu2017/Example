package cn.fishland.bookportal.bean.pojo;

import cn.fishland.bookportal.bean.BaseBean;

/**
 * ISBN、出版社、作者这种标签数据
 * 主要实现在子类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class Tag extends BaseBean {
    private static final long serialVersionUID = -2065450684436842468L;

    public Tag() {
    }

    /** 标签类型 */
    protected Integer type;
    /** 标签名称 */
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    protected String toJson() {
        return null;
    }
}
