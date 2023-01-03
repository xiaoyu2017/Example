package cn.fishland.bookmanager.service;

import cn.fishland.bookmanager.bean.pojo.Category;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 类别服务类
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface CategoryService {

    boolean save(Category... categories);

    boolean update(Category categories);

    boolean delete(Category... categories);

    /**
     * 查询指定页数内容
     * @param page 第几页，开始为1
     * @param num 每页显示数，默认10
     * @return List<Category>
     */
    PageInfo<Category> findAll(int page, int num);


    PageInfo<Category> findByParam(Category category);
}
