package cn.fishland.bookmanager.dao.impl;

import cn.fishland.bookmanager.bean.pojo.Category;
import cn.fishland.bookmanager.dao.CategoryDao;
import cn.fishland.bookmanager.tool.WebTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author xiaoyu
 * @version 1.0
 */
public class CategoryDaoImpl implements CategoryDao {

    @Override
    public boolean insert(Category... categories) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = getConnection();

            String sql = "insert into category(`name`) value(?);";

            prepareStatement = connection.prepareStatement(sql);

            for (Category category : categories) {
                prepareStatement.setString(1, category.name);
                prepareStatement.addBatch();
            }
            prepareStatement.executeBatch();

            return true;
        } catch (SQLException e) {
            System.out.println("save category error...");
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public boolean update(Category... categories) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = getConnection();

            String sql = "update category set `name` = ?, `sort` = ?, `status` = ? where id = ?;";

            prepareStatement = connection.prepareStatement(sql);

            for (Category category : categories) {
                prepareStatement.setString(1, category.name);
                prepareStatement.setInt(2, category.sort);
                prepareStatement.setBoolean(3, category.status);
                prepareStatement.setLong(4, category.id);
                prepareStatement.addBatch();
            }
            prepareStatement.executeBatch();

            return true;
        } catch (SQLException e) {
            System.out.println("save category error...");
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public boolean delete(Category... categories) {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = getConnection();

            String sql = "delete from category where id = ?;";

            prepareStatement = connection.prepareStatement(sql);

            for (Category category : categories) {
                prepareStatement.setLong(1, category.id);
                prepareStatement.addBatch();
            }
            prepareStatement.executeBatch();
            return true;
        } catch (SQLException e) {
            System.out.println("save category error...");
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public List<Category> find(int page, int num) {
        List<Category> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = getConnection();

            String sql = "select * from category order by `createTime` limit ?, ?";

            prepareStatement = connection.prepareStatement(sql);

            prepareStatement.setInt(1, num * (page - 1));
            prepareStatement.setInt(2, num);

            ResultSet resultSet = prepareStatement.executeQuery();

            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getLong("id"));
                category.setSort(resultSet.getInt("sort"));
                category.setStatus(resultSet.getBoolean("status"));
                category.setCreateTime(resultSet.getDate("createTime"));
                category.setUpdateTime(resultSet.getDate("updateTime"));
                category.setName(resultSet.getString("name"));
                list.add(category);
            }
            return list;
        } catch (SQLException e) {
            System.out.println("save category error...");
            return null;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }

    @Override
    public int count() {
        Connection connection = null;
        PreparedStatement prepareStatement = null;
        try {
            connection = getConnection();

            String sql = "select count(1) from category";

            prepareStatement = connection.prepareStatement(sql);

            ResultSet resultSet = prepareStatement.executeQuery();

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            System.out.println("save category error...");
            return -1;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException ignored) {
                }
            }
            if (prepareStatement != null) {
                try {
                    prepareStatement.close();
                } catch (SQLException ignored) {
                }
            }
        }
    }


    private Connection getConnection() {
        try {
            return DriverManager.getConnection(WebTool.dbUrl, WebTool.dbUser, WebTool.dbPassword);
        } catch (SQLException e) {
            System.out.println("获得数据库连接失败，请检查数据库是否开启》》》");
        }
        return null;
    }
}
