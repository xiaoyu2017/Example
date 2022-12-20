package cn.fishland.bookmanager.dao;

import cn.fishland.bookmanager.bean.pojo.Category;

import java.util.List;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface CategoryDao {

    boolean insert(Category... categories);

    boolean update(Category... categories);

    boolean delete(Category... categories);

    List<Category> find(int page, int num);

    int count();

}
