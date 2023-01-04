package cn.fishland.bookmanager.controller;

import cn.fishland.bookmanager.bean.pojo.Category;
import cn.fishland.bookmanager.bean.vo.CategoryVo;
import cn.fishland.bookmanager.bean.vo.ReturnData;
import cn.fishland.bookmanager.service.CategoryService;
import cn.fishland.bookmanager.service.impl.CategoryServiceImpl;
import cn.fishland.bookmanager.tool.WebTool;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 * 类别相关类请求控制类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
public class CategoryServlet extends HttpServlet {
    private static final long serialVersionUID = -7784350673473467125L;

    private final CategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 第一次请求类别管理页面
        String requestUri = req.getRequestURI();
        String[] pathArray = WebTool.handlerMapping(requestUri, "category");
        if (pathArray == null) {
            log.debug(String.format("category servlet get handler mapping error uri=[%s]", requestUri));
            return;
        }

        switch (pathArray[0]) {
            case "page":
                page(req, resp, pathArray[1]);
                break;
            default:
        }
    }

    private void page(HttpServletRequest req, HttpServletResponse resp, String s) {
        int page = Integer.parseInt(s);
        if (page < 0) {
            log.debug("category page number < 0");
            return;
        }

        CategoryVo categoryVo = new CategoryVo();
        String name = req.getParameter("name");
        if (name != null) {
            categoryVo.setName(name);
        }
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String createStart = req.getParameter("createStart");
            if (!WebTool.isBlank(createStart)) {
                categoryVo.setCreateStart(dateFormat.parse(createStart));
            }
            String createEnd = req.getParameter("createEnd");
            if (!WebTool.isBlank(createEnd)) {
                categoryVo.setCreateStart(dateFormat.parse(createEnd));
            }
            String updateStart = req.getParameter("updateStart");
            if (!WebTool.isBlank(updateStart)) {
                categoryVo.setCreateStart(dateFormat.parse(updateStart));
            }
            String updateEnd = req.getParameter("updateEnd");
            if (!WebTool.isBlank(updateEnd)) {
                categoryVo.setCreateStart(dateFormat.parse(updateEnd));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        req.setAttribute("categoryPageInfo", categoryService.findAll(page, 10, categoryVo));
        req.setAttribute("categoryVo", categoryVo);
        req.setAttribute("searchParam", "?" + WebTool.toGetUrlParam(categoryVo));
        try {
            req.getRequestDispatcher("/WEB-INF/views/page/category.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathArray = WebTool.handlerMapping(req.getRequestURI(), "category");
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
        switch (pathArray[0]) {
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
