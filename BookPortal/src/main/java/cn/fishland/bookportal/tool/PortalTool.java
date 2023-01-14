package cn.fishland.bookportal.tool;

import cn.fishland.bookportal.bean.pojo.AuthorTag;
import cn.fishland.bookportal.bean.pojo.Ebook;
import cn.fishland.bookportal.bean.pojo.FileAttachment;
import cn.fishland.bookportal.bean.pojo.PublisherTag;
import cn.fishland.bookportal.bean.vo.EbookVo;
import cn.fishland.bookportal.bean.vo.PageInfo;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

/**
 * 工具类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class PortalTool {

    private static DataSource dataSource;

    static {
        try {
            // 加载配置文件
            Properties prop = new Properties();
            prop.load(new FileInputStream(Objects.requireNonNull(PortalTool.class.getClassLoader().getResource("druid.properties")).getPath()));
            // 创建连接池
            dataSource = DruidDataSourceFactory.createDataSource(prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection connection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection connection, Statement statement) {
        if (connection != null) {
            try {
                connection.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<EbookVo> ebook2EbookVo(Map<Long, Ebook> ebookMap) {
        if (ebookMap == null || ebookMap.size() <= 0) {
            System.out.println("ebook2EbookVo ebookMap size is empty");
            return null;
        }
        List<EbookVo> ebookVos = new ArrayList<>();
        EbookVo ebookVo;
        for (Ebook ebook : ebookMap.values()) {
            ebookVo = new EbookVo();
            ebookVo.setId(ebook.getId());
            ebookVo.setCreateTime(ebook.getCreateTime());
            ebookVo.setUpdateTime(ebook.getUpdateTime());
            ebookVo.setBookName(ebook.getBookName());
            AuthorTag author = ebook.getAuthor();
            if (author != null) {
                ebookVo.setAuthor(author.getName());
            }
            PublisherTag publisher = ebook.getPublisher();
            if (publisher != null) {
                ebookVo.setPublisher(publisher.getName());
            }
            ebookVo.setYear(ebook.getYear());
            ebookVo.setLanguage(ebook.getLanguage());
            FileAttachment file = ebook.getFile();
            if (file != null) {
                ebookVo.setSize(file.getSize() + "");
                ebookVo.setSizeUnit(file.getSizeUnit());
                ebookVo.setExtension(file.getExtension());
            }
            ebookVo.setInfo("作者：" + ebookVo.getAuthor() + " 出版社：" + ebookVo.getPublisher() + " 时间："
                    + ebookVo.getYear() + " 语言：" + ebookVo.getLanguage() + " 大小：" + ebookVo.getSize()
                    + ebookVo.getSizeUnit() + " 类型：" + ebookVo.getExtension());
            ebookVos.add(ebookVo);
        }
        return ebookVos;
    }

    public static boolean isBlank(String str) {
        return str == null || (str.length() <= 0);
    }

    public static String[] parseUrlPath2Array(String path) {
        if (isBlank(path = path.trim())) {
            return null;
        }

        if (path.charAt(0) == '/') {
            path = path.substring(1);
        }

        return path.split("/");
    }

    public static String[] handlerMapping(String path, String servletFlag) {
        String[] strings = parseUrlPath2Array(path);

        int flag = 0;
        if (strings != null && strings.length > 0) {
            if (strings[1].equals(servletFlag)) {
                flag = 2;
            } else if (strings[0].equals("BookPortal")) {
                flag = 1;
            }
            String[] mappers = new String[strings.length - flag];
            System.arraycopy(strings, flag, mappers, 0, strings.length - flag);
            return mappers;
        } else {
            System.out.println("get request handler mapping error...");
            return null;
        }
    }

    public static <T> void pageInfo(List<T> tList, PageInfo<T> pageInfo) {
        pageInfo.setTotal(tList.size());
        pageInfo.setPages((tList.size() + pageInfo.getPageNum() - 2) / pageInfo.getPageNum());
        int fromIndex = (pageInfo.getPageNum() - 1) * pageInfo.getPageSize();
        int toIndex = pageInfo.getPageSize() > tList.size() ? tList.size() : pageInfo.getPageSize();
        pageInfo.setList(tList.subList(fromIndex, toIndex));
        pageInfo.setSize(pageInfo.getList().size());
        pageInfo.setPrePage(pageInfo.getPageNum() == 1 ? 1 : pageInfo.getPageNum() - 1);
        pageInfo.setNextPage(Objects.equals(pageInfo.getPageNum(), pageInfo.getPages()) ? pageInfo.getPages() : pageInfo.getPageNum() + 1);
    }

}
