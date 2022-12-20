package cn.fishland.bookmanager.controller;

import cn.fishland.bookmanager.bean.pojo.Category;
import cn.fishland.bookmanager.service.CategoryService;
import cn.fishland.bookmanager.service.impl.CategoryServiceImpl;
import cn.fishland.bookmanager.tool.WebTool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public class ViewsServlet extends HttpServlet {

    private CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathArray = WebTool.parseUrlPath2Array(req.getRequestURI());
        if (pathArray == null) {
            return;
        }
        String view = pathArray[pathArray.length - 1];
        switch (view) {
            case "category":
                category(req, resp);
                break;
            case "user":
                user(req, resp);
                break;
            default:
        }

        req.getRequestDispatcher("/WEB-INF/views/page/" + view + ".jsp").forward(req, resp);
    }

    private void user(HttpServletRequest req, HttpServletResponse resp) {
        long count = categoryService.count();
        List<Category> list = categoryService.find(1, 10);

        req.setAttribute("categoryList", list);
        req.setAttribute("title", "类别管理");
        req.setAttribute("startRow", 0);
        req.setAttribute("endRow", 10);
        req.setAttribute("count", count);
        req.setAttribute("page", (count + 10) / 10);
        req.setAttribute("indexPage", 1);
    }

    private void category(HttpServletRequest req, HttpServletResponse res) {
        long count = categoryService.count();
        List<Category> list = categoryService.find(1, 10);

        req.setAttribute("categoryList", list);
        req.setAttribute("title", "类别管理");
        req.setAttribute("startRow", 0);
        req.setAttribute("endRow", 10);
        req.setAttribute("count", count);
        req.setAttribute("page", (count + 10) / 10);
        req.setAttribute("indexPage", 1);
    }
}
