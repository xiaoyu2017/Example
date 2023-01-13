package cn.fishland.bookportal.tool;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileReader;
import java.sql.Connection;
import java.util.Properties;

/**
 * 工具类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class PortalTool {

    public static void main(String[] args) throws Exception {
        // 加载配置文件
        Properties prop = new Properties();
        prop.load(new FileReader("druid.properties"));
        // 创建连接池
        DataSource dataSource = DruidDataSourceFactory.createDataSource(prop);
        // 获得连接Connection
        Connection connection = dataSource.getConnection();

        System.out.println(connection);
    }

}
