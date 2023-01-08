package cn.fishland.bookmanager.service.impl;

import cn.fishland.bookmanager.bean.pojo.Category;
import cn.fishland.bookmanager.bean.vo.CategoryVo;
import cn.fishland.bookmanager.service.CategoryService;
import cn.fishland.bookmanager.tool.WebTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Override
    public boolean save(Category... categories) {
        if (categories == null || categories.length <= 0) {
            System.out.println("未由内容需要保存");
            return false;
        }
        SqlSession sqlSession = null;
        try {
            sqlSession = WebTool.sqlSession();
            sqlSession.insert("categoryMapper.save", categories);
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
    public boolean update(Category categories) {
        if (categories == null) {
            System.out.println("未由内容需要修改");
            return false;
        }
        SqlSession sqlSession = null;
        try {
            sqlSession = WebTool.sqlSession();
            sqlSession.update("categoryMapper.updateById", categories);
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
    public boolean delete(Category... categories) {
        if (categories == null || categories.length <= 0) {
            System.out.println("未由内容需要删除");
            return false;
        }
        Long[] ids = new Long[categories.length];
        for (int i = 0; i < categories.length; i++) {
            ids[i] = categories[i].getId();
        }
        SqlSession sqlSession = null;
        try {
            sqlSession = WebTool.sqlSession();
            sqlSession.delete("categoryMapper.removeById", ids);
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
    public PageInfo<Category> findAll(int page, int num, CategoryVo categoryVo) {
        if (page <= 0 || num <= 0) {
            System.out.println("page 或 num 必须大于0");
            return null;
        }
        SqlSession sqlSession = null;
        try {
            sqlSession = WebTool.sqlSession();
            PageHelper.startPage(page, num);
            List<Category> list = sqlSession.selectList("categoryMapper.findAll", categoryVo);
            return new PageInfo<>(list, 5);
        } catch (Exception e) {
            log.debug(String.format("insert category error=[%s]", e.getMessage()));
            if (sqlSession != null) {
                sqlSession.rollback();
            }
            return null;
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

}
