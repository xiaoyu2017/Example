package cn.fishland.bookmanager.tool;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * 工具类
 *
 * @author xiaoyu
 * @version 1.0
 */
@Slf4j
public class WebTool {

    public static String LOGIN_VIEW = "/BookManager/view/login";
    public static String LOGIN_API = "/BookManager/api/user/login";
    public static String dbUrl = "jdbc:mysql://localhost:33060/BookManager?useUnicode=true&characterEncoding=utf-8&useSSL=true&serverTimezone=UTC";
    public static String dbUser = "root";
    public static String dbPassword = "root";

    public static String FILE_ATTACHMENT_PATH = "/Users/yujiangzhong/Documents/BookManager/file";
    public static String IMAGE_ATTACHMENT_PATH = "/Users/yujiangzhong/Documents/BookManager/image";
    public static String TEMP_ATTACHMENT_PATH = "/Users/yujiangzhong/Documents/BookManager/temp";

    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            /*初始化mybatis*/
            String path = "mybatis.xml";
            InputStream inputStream = Resources.getResourceAsStream(path);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            /*初始化附件文件*/
            File file = new File(FILE_ATTACHMENT_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(IMAGE_ATTACHMENT_PATH);
            if (!file2.exists()) {
                file2.mkdirs();
            }
            File file3 = new File(TEMP_ATTACHMENT_PATH);
            if (!file3.exists()) {
                file3.mkdirs();
            }
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

    public static String[] handlerMapping(String path, String servletFlag) {
        String[] strings = parseUrlPath2Array(path);
        int flag = 0;
        if (strings != null && strings.length > 0) {
            if (strings[2].equals(servletFlag)) {
                flag = 3;
            } else if (strings[0].equals("BookManager")) {
                flag = 1;
            } else if (strings[1].equals("api") || strings[1].equals("view")) {
                flag = 2;
            }
            String[] mappers = new String[strings.length - flag];
            System.arraycopy(strings, flag, mappers, 0, strings.length - flag);
            return mappers;
        } else {
            log.debug("get request handler mapping error...");
            return null;
        }
    }

    public static <T> String toGetUrlParam(T t) {
        try {
            StringBuilder stringBuffer = new StringBuilder();

            List<Field> fields = new ArrayList<>();
            fields.addAll(Arrays.asList(t.getClass().getFields()));
            fields.addAll(Arrays.asList(t.getClass().getDeclaredFields()));

            for (Field field : fields) {
                // 忽略访问权限
                field.setAccessible(true);
                String fieldName = field.getName();
                if (!"serialVersionUID".equals(fieldName)) {
                    stringBuffer.append(fieldName);
                    stringBuffer.append("=");
                    Object obj = field.get(t);
                    if (obj != null) {
                        stringBuffer.append(URLEncoder.encode(obj + "", "UTF-8"));
                    }
                    stringBuffer.append("&");
                }
            }

            return String.valueOf(stringBuffer.substring(0, stringBuffer.length() - 1));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
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
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
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

    private static String[] SIZE_UNIT = {"B", "KB", "MB", "GB", "TB"};

    public static String unitChange(Double size) {
        for (String unit : SIZE_UNIT) {
            if (size >= 1024) {
                size = size / 1024;
            } else {
                return size + "|" + unit;
            }
        }
        return null;
    }

}
