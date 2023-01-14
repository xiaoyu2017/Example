package cn.fishland.bookportal.controller;

import cn.fishland.bookportal.bean.pojo.ImageAttachment;
import cn.fishland.bookportal.bean.vo.EbookVo;
import cn.fishland.bookportal.bean.vo.PageInfo;
import cn.fishland.bookportal.service.EbookService;
import cn.fishland.bookportal.service.impl.EbookServiceImpl;
import cn.fishland.bookportal.tool.PortalTool;
import com.alibaba.fastjson2.JSON;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

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
        String[] pathArray = PortalTool.handlerMapping(req.getRequestURI(), "ebook");
        if (pathArray == null) {
            System.out.println("ebook get uri ia error");
            return;
        }

        if ("view".equals(pathArray[0])) {
            switch (pathArray[1]) {
                case "info":
                    showInfo(req, resp, pathArray[2]);
                    break;
                default:
                    System.out.println("get view uri not find");
            }
        } else if ("api".equals(pathArray[0])) {
            switch (pathArray[1]) {
                case "img":
                    showImg(req, resp, pathArray[2]);
                    break;
                default:
                    System.out.println("get api uri not find");
            }
        }

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
                default:
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

    private void showImg(HttpServletRequest req, HttpServletResponse resp, String imgId) {
        EbookService ebookService = new EbookServiceImpl();
        ImageAttachment imageAttachment = ebookService.findAttachmentById(imgId);

        if (imageAttachment != null) {
            File file = new File(imageAttachment.getFilePath());
            if (!file.exists()) {
                System.out.println("local path attachment not exists");
                return;
            }

            // 直接返回图片后缀
            resp.setContentType(imageAttachment.getExtension());
            // 下载
            // resp.addHeader("Content-Disposition", "attachment;fileName=" + attachment.getName());
            resp.setHeader("Pragma", "No-cache");
            resp.setHeader("Cache-Control", "no-cache");

            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = resp.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
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

    private void showInfo(HttpServletRequest req, HttpServletResponse resp, String id) {
        if (PortalTool.isBlank(id)) {
            System.out.println("ebook id is empty");
            return;
        }
        long eid = Long.parseLong(id);

        EbookService ebookService = new EbookServiceImpl();
        EbookVo ebookVo = ebookService.findById(eid);
        req.setAttribute("ebookVo", ebookVo);

        try {
            req.getRequestDispatcher("/info.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
