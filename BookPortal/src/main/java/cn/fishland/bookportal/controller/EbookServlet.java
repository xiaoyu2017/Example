package cn.fishland.bookportal.controller;

import cn.fishland.bookportal.bean.vo.EbookVo;
import cn.fishland.bookportal.bean.vo.PageInfo;
import cn.fishland.bookportal.service.EbookService;
import cn.fishland.bookportal.service.impl.EbookServiceImpl;
import cn.fishland.bookportal.tool.PortalTool;
import com.alibaba.fastjson2.JSON;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 电子书控制类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class EbookServlet extends HttpServlet {
    private static final long serialVersionUID = 4515687931388228126L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathArray = PortalTool.handlerMapping(req.getRequestURI(), "ebook");
        if (pathArray == null) {
            System.out.println("ebook get uri ia error");
            return;
        }

        if ("view".equals(pathArray[0])) {
            switch (pathArray[1]) {

            }
        } else if ("api".equals(pathArray[0])) {
            switch (pathArray[1]) {
                case "search":
                    apiSearch(req, resp);
                    break;
                default:
                    System.out.println("post api uri not find");
            }
        }

    }

    private void apiSearch(HttpServletRequest req, HttpServletResponse resp) {
        int page = 1, pageNum = 15;
        // 获得分页信息
        String pageStr = req.getParameter("page");
        if (!PortalTool.isBlank(pageStr)) {
            page = Integer.parseInt(pageStr);
        }
        String pageNumStr = req.getParameter("pageNum");
        if (!PortalTool.isBlank(pageNumStr)) {
            pageNum = Integer.parseInt(pageNumStr);
        }
        String search = req.getParameter("search").trim();
        if (PortalTool.isBlank(search)) {
            System.out.println("search str is empty");
            return;
        }

        EbookService ebookService = new EbookServiceImpl();
        PageInfo<EbookVo> pageInfo = new PageInfo<>();
        pageInfo.setPageNum(page);
        pageInfo.setPageSize(pageNum);
        PortalTool.pageInfo(ebookService.searchAll(search), pageInfo);

        try {
            resp.getWriter().println(JSON.toJSONString(pageInfo));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
