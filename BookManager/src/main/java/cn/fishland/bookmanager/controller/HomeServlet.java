package cn.fishland.bookmanager.controller;

import cn.fishland.bookmanager.tool.WebTool;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 主页
 *
 * @author xiaoyu
 * @version 1.0
 */
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("title", "图书管理");


        WebTool.forward(req, resp, "/WEB-INF/views/index.jsp");
    }
}
