package cn.fishland.bookmanager.controller;

import cn.fishland.bookmanager.bean.pojo.*;
import cn.fishland.bookmanager.bean.vo.ReturnData;
import cn.fishland.bookmanager.service.EbookService;
import cn.fishland.bookmanager.service.impl.EbookServiceImpl;
import cn.fishland.bookmanager.tool.WebTool;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.ProgressListener;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 电子书控制台
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
public class EbookServlet extends HttpServlet {
    private static final long serialVersionUID = -1103661426684073845L;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String[] pathArray = WebTool.handlerMapping(req.getRequestURI(), "ebook");
        if (pathArray == null) {
            log.debug("无法获得类别数据操作请求uir");
            resp.getWriter().print(JSON.toJSONString(new ReturnData("请求链接无法处理")));
            return;
        }

        switch (pathArray[0]) {
            case "add":
                ebookAdd(req, resp);
                break;
            default:
                log.debug("请求链接有问题，请联系管理员！");
        }

    }

    private void ebookAdd(HttpServletRequest req, HttpServletResponse resp) {
        if (!ServletFileUpload.isMultipartContent(req)) {
            log.debug("add ebook expect have attachment but is null");
            return;
        }

        // 建立缓冲区
        File file = new File(WebTool.TEMP_ATTACHMENT_PATH);
        DiskFileItemFactory factory = getDiskFileItemFactory(file);

        // 等待上传完成
        ServletFileUpload servletFileUpload = getServletFileUpload(factory);

        // 接收对象
        Ebook ebook = new Ebook();
        // 处理上传结果
        uploadParseRequest(servletFileUpload, req, ebook);

        // 保存数据库
        EbookService ebookService = new EbookServiceImpl();
        if (!ebookService.save(ebook)) {
            log.error("servlet save ebook error");
            return;
        }

        try {
            // 上传成功跳转
            resp.sendRedirect(req.getContextPath() + "/view/ebook");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public DiskFileItemFactory getDiskFileItemFactory(File file) {
        DiskFileItemFactory factory = new DiskFileItemFactory();
        // 通过这个工厂设置一个缓冲区,当上传的文件大于这个缓冲区的时候,将他放到临时文件中;
        // 缓冲区大小为1M
        factory.setSizeThreshold(1024 * 1024);
        // 临时目录的保存目录,需要一个file
        factory.setRepository(file);
        return factory;
    }

    public ServletFileUpload getServletFileUpload(DiskFileItemFactory factory) {
        ServletFileUpload upload = new ServletFileUpload(factory);
        // 监听长传进度
        upload.setProgressListener(new ProgressListener() {
            // pBYtesRead:已读取到的文件大小
            // pContextLength:文件大小
            @Override
            public void update(long pBytesRead, long pContentLength, int pItems) {
                log.info(String.format("总大小：%s， 已上传：%s", pContentLength, pBytesRead));
            }
        });
        // 处理乱码问题
        upload.setHeaderEncoding("UTF-8");
        // 设置单个文件的最大值
        upload.setFileSizeMax(1024 * 1024 * 10 * 50);
        // 设置总共能够上传文件的大小
        // 1024 = 1kb * 1024 = 1M * 10 * 50 = 500м
        return upload;
    }

    public void uploadParseRequest(ServletFileUpload upload, HttpServletRequest request, Ebook ebook) {
        try {
            // 获得表单每个对象，包括普通字段和文件字段
            List<FileItem> fileItems = upload.parseRequest(request);

            // 遍历处理
            for (FileItem fileItem : fileItems) {
                // 判断是普通属性还是附件属性
                if (fileItem.isFormField()) {
                    // 普通字段处理
                    parseTextField(fileItem, ebook);
                } else {
                    // 文件字段处理
                    parseFileField(fileItem, ebook);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void parseTextField(FileItem fileItem, Ebook ebook) {
        if (fileItem == null || ebook == null) {
            return;
        }
        try {
            String value = fileItem.getString("UTF-8");

            switch (fileItem.getFieldName()) {
                case "bookName":
                    ebook.setBookName(value);
                    break;
                case "edition":
                    ebook.setEdition(value);
                    break;
                case "year":
                    ebook.setYear(value);
                    break;
                case "language":
                    ebook.setLanguage(value);
                    break;
                case "pages":
                    ebook.setPages(value);
                    break;
                case "bookmark":
                    ebook.setBookmark(Boolean.valueOf(value));
                    break;
                case "summary":
                    ebook.setSummary(value);
                    break;
                case "publisher":
                    ebook.setPublisher(new PublisherTag(value));
                    break;
                case "author":
                    ebook.setAuthor(new AuthorTag(value));
                    break;
                case "isbn":
                    String[] split = value.split(",");
                    if (split.length > 0) {
                        List<IsbnTag> list = new ArrayList<>();
                        for (String isbn : split) {
                            list.add(new IsbnTag(isbn));
                        }
                        ebook.setIsbn(list);
                    }
                    break;
                case "category":
                    String[] categoryArray = value.split(",");
                    if (categoryArray.length > 0) {
                        List<Category> list = new ArrayList<>();
                        for (String category : categoryArray) {
                            list.add(new Category(category));
                        }
                        ebook.setCategories(list);
                    }
                    break;
                default:
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseFileField(FileItem fileItem, Ebook ebook) {
        try {
            // 字段名
            String fieldName = fileItem.getFieldName();

            // 文件全路径名
            String filePathName = fileItem.getName();
            if (WebTool.isBlank(filePathName)) {
                log.error(String.format("文件名不合法，文件名={%S}", fieldName));
                return;
            }

            // 文件名
            String name = filePathName.substring(filePathName.lastIndexOf("/") + 1);
            // 文件后缀名
            String extension = filePathName.substring(filePathName.lastIndexOf(".") + 1);
            // 文件大小
            long size = fileItem.getSize();
            // 文件大小单位
            String sizeUnit = "B";
            String unitChange = WebTool.unitChange((double) size);
            if (!WebTool.isBlank(unitChange)) {
                String[] split = unitChange.split("|");
                size = Long.parseLong(split[0]);
                sizeUnit = String.valueOf(split[1]);
            }

            // 文件aid
            String aid = UUID.randomUUID().toString().replaceAll("-", "");

            // 物理文件地址
            String filePath;

            //判断是文件还是图片
            if ("file".equals(fieldName)) {
                filePath = WebTool.FILE_ATTACHMENT_PATH + "/" + aid + "." + extension;
                FileAttachment attachment = new FileAttachment();
                // 保存附件信息
                attachment.setAid(aid);
                attachment.setName(name);
                attachment.setSize((double) size);
                attachment.setSizeUnit(sizeUnit);
                attachment.setExtension(extension);
                attachment.setFilePath(filePath);
                ebook.setFile(attachment);
            } else if ("image".equals(fieldName)) {
                filePath = WebTool.IMAGE_ATTACHMENT_PATH + "/" + aid + "." + extension;
                ImageAttachment attachment = new ImageAttachment();
                // 保存附件信息
                attachment.setAid(aid);
                attachment.setName(name);
                attachment.setSize((double) size);
                attachment.setSizeUnit(sizeUnit);
                attachment.setExtension(extension);
                attachment.setFilePath(filePath);
                ebook.setImage(attachment);
            } else {
                log.debug("ebook form upload unknown file...");
                return;
            }

            // 获得文件上传的流
            InputStream inputStream = fileItem.getInputStream();

            // 创建一个文件输出流写文件
            FileOutputStream fos = new FileOutputStream(filePath);
            // 创建一个缓冲区
            byte[] buffer = new byte[1024 * 1024];
            // 判断是否读取完毕
            int len = 0;
            // 如果大于0说明还存在数据;
            while ((len = inputStream.read(buffer)) > 0) {
                fos.write(buffer, 0, len);
            }
            // 关闭流
            fos.close();
            inputStream.close();
            // 上传成功,清除临时文件
            fileItem.delete();


        } catch (Exception e) {
            log.error(String.format("upload file error=[%s]", e.getMessage()));
        }
    }
}
