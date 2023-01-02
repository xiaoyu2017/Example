package cn.fishland.bookmanager.tool;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 工具类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class WebTool {

    public static String LOGIN_VIEW = "/BookManager/view/login";
    public static String LOGIN_API = "/BookManager/api/user/login";
    public static String dbUrl = "jdbc:mysql://localhost:33060/BookManager?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
    public static String dbUser = "root";
    public static String dbPassword = "root";

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String path = "mybatis.xml";
            InputStream inputStream = Resources.getResourceAsStream(path);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        } catch (Exception e) {
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

    public static String[] handlerMapping(String path) {
        String[] strings = parseUrlPath2Array(path);
        int flag = 0;
        if (strings.length > 0) {
            if (strings[0].equals("BookManager")) {
                flag = 1;
            }
        }
        System.arraycopy(strings, flag, strings, 0, strings.length - 1);
        return strings;
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


    public static Object getMapper(Class clazz) throws IOException {
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession.getMapper(clazz);
    }

    public static SqlSession sqlSession() {
        return sqlSessionFactory.openSession();
    }

    public static boolean letGo(String path) {
        return LOGIN_VIEW.equalsIgnoreCase(path.trim()) || LOGIN_API.equalsIgnoreCase(path.trim());
    }

    public static DefaultKaptcha defaultKaptcha() {
        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        Properties properties = new Properties();
        // 图片边框
        properties.setProperty("kaptcha.border", "no");
        // 边框颜色
        properties.setProperty("kaptcha.border.color", "black");
        //边框厚度
        properties.setProperty("kaptcha.border.thickness", "1");
        // 图片宽
        properties.setProperty("kaptcha.image.width", "120");
        // 图片高
        properties.setProperty("kaptcha.image.height", "50");
        //图片实现类
        properties.setProperty("kaptcha.producer.impl", "com.google.code.kaptcha.impl.DefaultKaptcha");
        //文本实现类
        properties.setProperty("kaptcha.textproducer.impl", "com.google.code.kaptcha.text.impl.DefaultTextCreator");
        //文本集合，验证码值从此集合中获取
        properties.setProperty("kaptcha.textproducer.char.string", "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        //验证码长度
        properties.setProperty("kaptcha.textproducer.char.length", "4");
        //字体
        properties.setProperty("kaptcha.textproducer.font.names", "宋体");
        //字体颜色
        properties.setProperty("kaptcha.textproducer.font.color", "black");
        //文字间隔
        properties.setProperty("kaptcha.textproducer.char.space", "4");
        //干扰实现类
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.DefaultNoise");
        //干扰颜色
        properties.setProperty("kaptcha.noise.color", "blue");
        //干扰图片样式
        properties.setProperty("kaptcha.obscurificator.impl", "com.google.code.kaptcha.impl.WaterRipple");
        //背景实现类
        properties.setProperty("kaptcha.background.impl", "com.google.code.kaptcha.impl.DefaultBackground");
        //背景颜色渐变，结束颜色
        properties.setProperty("kaptcha.background.clear.to", "white");
        //文字渲染器
        properties.setProperty("kaptcha.word.impl", "com.google.code.kaptcha.text.impl.DefaultWordRenderer");
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }
}
