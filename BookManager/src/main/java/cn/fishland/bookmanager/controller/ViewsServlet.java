package cn.fishland.bookmanager.controller;

import cn.fishland.bookmanager.service.CategoryService;
import cn.fishland.bookmanager.service.impl.CategoryServiceImpl;
import cn.fishland.bookmanager.tool.WebTool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public class ViewsServlet extends HttpServlet {

    private CategoryService categoryService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathArray = WebTool.handlerMapping(req.getRequestURI(), null);
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
            case "login":
                login(req, resp);
                break;
            default:
        }

        req.getRequestDispatcher("/WEB-INF/views/page/" + view + ".jsp").forward(req, resp);
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("登录界面的预处理...");
    }

    private void user(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void category(HttpServletRequest req, HttpServletResponse res) {
        categoryService = new CategoryServiceImpl();
        req.setAttribute("categoryPageInfo", categoryService.findAll(1, 10));
    }
}
