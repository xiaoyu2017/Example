package cn.fishland.bookmanager.bean.pojo;

import cn.fishland.bookmanager.bean.BaseBean;

import java.util.List;
import java.util.Objects;

/**
 * 菜单类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class Menu extends BaseBean {
    private String name;
    private String icon;
    private String link;
    private Long parent;
    private List<Menu> childs;

    public Menu() {
    }

    public Menu(String name, String icon, String link) {
        this.name = name;
        this.icon = icon;
        this.link = link;
    }

    public Menu(String name, String icon, String link, Long parent) {
        this.name = name;
        this.icon = icon;
        this.link = link;
        this.parent = parent;
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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }

    public List<Menu> getChilds() {
        return childs;
    }

    public void setChilds(List<Menu> childs) {
        this.childs = childs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Menu menu = (Menu) o;
        return Objects.equals(name, menu.name) && Objects.equals(icon, menu.icon) && Objects.equals(link, menu.link) && Objects.equals(parent, menu.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, icon, link, parent);
    }

    @Override
    public String toString() {
        return "Menu{" +
                "name='" + name + '\'' +
                ", icon='" + icon + '\'' +
                ", link='" + link + '\'' +
                ", parent=" + parent +
                ", childs=" + childs +
                "} " + super.toString();
    }
}
