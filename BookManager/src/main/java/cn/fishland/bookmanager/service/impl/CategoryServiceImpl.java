package cn.fishland.bookmanager.service.impl;

import cn.fishland.bookmanager.bean.pojo.Category;
import cn.fishland.bookmanager.dao.CategoryDao;
import cn.fishland.bookmanager.dao.impl.CategoryDaoImpl;
import cn.fishland.bookmanager.service.CategoryService;
import cn.fishland.bookmanager.tool.WebTool;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * 类别服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
public class CategoryServiceImpl implements CategoryService {

    CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public boolean save(Category... categories) {
        if (categories == null || categories.length <= 0) {
            System.out.println("未由内容需要保存");
            return false;
        }
        SqlSession sqlSession = null;
        try {
            sqlSession = WebTool.sqlSession();
            for (Category category : categories) {
                sqlSession.insert("categoryMapper.add", category);
                log.debug(String.format("insert category success=[%s]", category));
            }
            sqlSession.commit();
        } catch (Exception e) {
            log.debug(String.format("insert category error=[%s]", e.getMessage()));
            if (sqlSession != null) {
                sqlSession.rollback();
            }
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
        return true;
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

    @Override
    public Category findById(String id) {
        return null;
    }

}
