package cn.fishland.bookmanager.controller;

import cn.fishland.bookmanager.bean.pojo.Category;
import cn.fishland.bookmanager.bean.vo.ReturnData;
import cn.fishland.bookmanager.service.CategoryService;
import cn.fishland.bookmanager.service.impl.CategoryServiceImpl;
import cn.fishland.bookmanager.tool.WebTool;
import com.alibaba.fastjson2.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 类别相关类请求控制类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = -7784350673473467125L;

    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一次请求类别管理页面
        String[] urlPaths = WebTool.parseUrlPath2Array(req.getContextPath());

        if (urlPaths == null) {
            // 表示请求类别管理页面相关信息请求


            return;
        }

        switch (urlPaths[0]) {
            case "page":
                System.out.println("page");
                break;
            case "page1":
                System.out.println("page1");
                break;
            case "page2":
                System.out.println("√2");
                break;
            default:
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String[] pathArray = WebTool.parseUrlPath2Array(req.getRequestURI());
        if (pathArray == null) {
            System.out.println("无法获得类别数据操作请求uir");
            resp.getWriter().print(JSON.toJSONString(new ReturnData("请求链接无法处理")));
            return;
        }

        // 获得类别对象
        String name = req.getParameter("name");
        String sort = req.getParameter("sort");
        String status = req.getParameter("status");
        String id = req.getParameter("id");

        Category category = new Category();
        if (!WebTool.isBlank(id)) {
            category.setId(Long.valueOf(id));
        }
        if (name != null) {
            category.setName(name);
        }
        if (sort != null) {
            category.setSort(Integer.valueOf(sort));
        }
        if (status != null) {
            category.setStatus("on".equalsIgnoreCase(status));
        }

        ReturnData returnData;
        switch (pathArray[pathArray.length - 1]) {
            case "add":
                returnData = categoryAdd(category);
                break;
            case "delete":
                returnData = categoryDelete(category);
                break;
            case "update":
                returnData = categoryUpdate(category);
                break;
            default:
                System.out.println("请求链接有问题，请联系管理员！");
                returnData = new ReturnData("请求链接有问题，请联系管理员！");
        }

        returnData.setTo(WebTool.addressName(req) + "/view/category");
        resp.getWriter().print(JSON.toJSONString(returnData));
    }


    private ReturnData categoryAdd(Category category) {
        categoryService.save(category);
        return new ReturnData("保存成功！");
    }


    private ReturnData categoryDelete(Category category) {
        categoryService.delete(category);
        return new ReturnData("删除成功！");
    }


    private ReturnData categoryUpdate(Category category) {
        categoryService.update(category);
        return new ReturnData("修改成功！");
    }
}
