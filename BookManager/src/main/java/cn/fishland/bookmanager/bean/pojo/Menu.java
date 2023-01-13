package cn.fishland.bookmanager.bean.pojo;

import cn.fishland.bookmanager.bean.BaseBean;
import com.alibaba.fastjson2.JSON;

import java.util.List;
import java.util.Objects;

/**
 * 菜单类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class Menu extends BaseBean {
    private static final long serialVersionUID = -4520157129066294361L;

    private String name;
    private String icon;
    private String link;
    private String parent;
    private List<Menu> children;
    private Menu parentMenu;

    public Menu() {
    }

    public Menu(String name, String icon, String link) {
        this.name = name;
        this.icon = icon;
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public List<Menu> getChildren() {
        return children;
    }

    public void setChildren(List<Menu> children) {
        this.children = children;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void setParentMenu(Menu parentMenu) {
        this.parentMenu = parentMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;
        return Objects.equals(name, menu.name) && Objects.equals(icon, menu.icon) && Objects.equals(link, menu.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, icon, link);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", status=" + status +
                ", sort=" + sort +
                ", name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", link='" + link + '\'' +
                ", parent='" + parent + '\'' +
                ", childs=" + children +
                ", parentMenu=" + parentMenu +
                "} " + super.toString();
    }

    @Override
    public String toJson() {
        return JSON.toJSONString(this);
    }
}
