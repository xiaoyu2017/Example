package cn.fishland.bookmanager.mapper;

import cn.fishland.bookmanager.bean.pojo.Menu;

import java.util.List;

/**
 * 菜单Mapper
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface MenuMapper {

    void insert(Menu menu);

    void delete(Menu menu);

    void update(Menu menu);

    List<Menu> selectAll();



}
