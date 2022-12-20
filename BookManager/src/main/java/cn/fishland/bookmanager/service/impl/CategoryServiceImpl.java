package cn.fishland.bookmanager.service.impl;

import cn.fishland.bookmanager.bean.pojo.Category;
import cn.fishland.bookmanager.dao.CategoryDao;
import cn.fishland.bookmanager.dao.impl.CategoryDaoImpl;
import cn.fishland.bookmanager.service.CategoryService;

import java.util.List;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public boolean save(Category... categories) {
        if (categories == null || categories.length <= 0) {
            System.out.println("未由内容需要保存");
            return false;
        }
        return categoryDao.insert(categories);
    }

    @Override
    public boolean update(Category... categories) {
        if (categories == null || categories.length <= 0) {
            System.out.println("未由内容需要修改");
            return false;
        }
        return categoryDao.update(categories);
    }

    @Override
    public boolean delete(Category... categories) {
        if (categories == null || categories.length <= 0) {
            System.out.println("未由内容需要删除");
            return false;
        }
        return categoryDao.delete(categories);
    }

    @Override
    public List<Category> find(int page, int num) {
        if (page <= 0 || num <= 0) {
            System.out.println("page 或 num 必须大于0");
            return null;
        }
        return categoryDao.find(page, num);
    }

    @Override
    public long count() {
        return categoryDao.count();
    }

}
