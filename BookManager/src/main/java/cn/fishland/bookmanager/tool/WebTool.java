package cn.fishland.bookmanager.tool;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 工具类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class WebTool {

    public static String dbUrl = "jdbc:mysql://localhost:33060/BookManager?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
    public static String dbUser = "root";
    public static String dbPassword = "root";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("无法注册mysql驱动类。。。");
        }
    }

    /**
     * 通过给定的路径，将每个/分割符之间数据存储在数组中
     *
     * @param path 请求路径
     * @return 字符串数组
     */
    public static String[] parseUrlPath2Array(String path) {
        if (isBlank(path = path.trim())) {
            return null;
        }

        if (path.charAt(0) == '/') {
            path = path.substring(1);
        }

        return path.split("/");
    }

    /**
     * 判断字符串是否为空
     * ""=true
     * null=true
     *
     * @return boolean
     */
    public static boolean isBlank(String str) {
        return str == null || (str.length() <= 0);
    }

    public static void main(String[] args) {
        String x = "/page/1/0";

        System.out.println(parseUrlPath2Array(x));
    }


    /**
     * 请求转发
     *
     * @param request  http请求
     * @param response http响应
     * @param viewPath 视图文件路径
     * @throws ServletException Servlet exception
     * @throws IOException      IO exception
     */
    public static void forward(ServletRequest request, ServletResponse response, String viewPath) throws ServletException, IOException {
        request.getRequestDispatcher(viewPath).forward(request, response);
    }

    public static String addressName(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
