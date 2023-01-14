package cn.fishland.bookportal.dao;

import cn.fishland.bookportal.bean.pojo.*;
import cn.fishland.bookportal.bean.vo.EbookVo;
import cn.fishland.bookportal.tool.PortalTool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电子书数据库操作类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class EbookDao {

    public Map<Long, Ebook> selectAll(String search) {
        Map<Long, Ebook> ebooks = new HashMap<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet;
        try {
            connection = PortalTool.connection();
            if (connection == null) {
                System.out.println("EbookDao get connection error");
                return null;
            }

            // 书名进行查询
            String bookName = "select * from ebook where book_name like concat('%',?,'%')";
            statement = connection.prepareStatement(bookName);
            statement.setString(1, search);
            resultSet = statement.executeQuery();
            resultSet2Ebook(ebooks, resultSet);

            // 作者进行查询
            String tag = "select e.id, e.createTime, e.updateTime, e.sort, e.status, e.book_name, e.edition," +
                    " e.year, e.language, e.pages, e.bookmark, e.summary from tag t right join ebook_tag et" +
                    " on t.id = et.tid right join ebook e on et.eid = e.id where t.type = ? and t.name = ?;";
            statement = connection.prepareStatement(tag);
            statement.setInt(1, 2);
            statement.setString(2, search);
            resultSet = statement.executeQuery();
            resultSet2Ebook(ebooks, resultSet);

            // 出版社查询
            statement.setInt(1, 1);
            statement.setString(2, search);
            resultSet = statement.executeQuery();
            resultSet2Ebook(ebooks, resultSet);

            // ISBN查询
            statement.setInt(1, 0);
            statement.setString(2, search);
            resultSet = statement.executeQuery();
            resultSet2Ebook(ebooks, resultSet);

            // 根据Ebook查询所有
            String ebookIdStr = null;
            if (ebooks.size() > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Ebook ebook : ebooks.values()) {
                    stringBuilder.append(ebook.getId());
                    stringBuilder.append(",");
                }
                ebookIdStr = stringBuilder.substring(0, stringBuilder.length() - 1);
            }

            String selectTagByEid = "select et.eid, t.id, t.createTime, t.updateTime, t.sort, t.status, t.name," +
                    " t.type from ebook_tag et right join tag t on et.tid = t.id where et.eid in (?);";
            // 查询所有IsbnTag
            statement = connection.prepareStatement(selectTagByEid);
            statement.setString(1, ebookIdStr);
            resultSet = statement.executeQuery();
            resultSet2Tag(ebooks, resultSet);

            // 查询所有附件
            String selectAttachmentByEid = "select * from attachment a where parent in (?);";
            statement = connection.prepareStatement(selectAttachmentByEid);
            statement.setString(1, ebookIdStr);
            resultSet = statement.executeQuery();
            resultSet2Attachment(ebooks, resultSet);

            return ebooks;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            PortalTool.close(connection, statement);
        }
    }

    private void resultSet2Ebook(Map<Long, Ebook> ebooks, ResultSet resultSet) {
        try {
            if (ebooks == null) {
                System.out.println("ebook list is null");
                return;
            }
            if (resultSet == null) {
                System.out.println("ebook resultSet is null");
                return;
            }

            Ebook ebook;
            while (resultSet.next()) {
                ebook = new Ebook();
                ebook.setId(resultSet.getLong("id"));
                ebook.setBookName(resultSet.getString("book_name"));
                ebook.setEdition(resultSet.getString("edition"));
                ebook.setYear(resultSet.getString("year"));
                ebook.setLanguage(resultSet.getString("language"));
                ebook.setPages(resultSet.getString("pages"));
                ebook.setBookmark(resultSet.getBoolean("bookmark"));
                ebook.setSummary(resultSet.getString("summary"));
                ebooks.put(ebook.getId(), ebook);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void resultSet2Tag(Map<Long, Ebook> ebooks, ResultSet resultSet) {
        if (resultSet == null) {
            System.out.println("tag resultSet is null");
            return;
        }
        Tag tag;

        try {
            while (resultSet.next()) {
                int type = resultSet.getInt("type");

                switch (type) {
                    case 0:
                        tag = new IsbnTag();
                        break;
                    case 1:
                        tag = new PublisherTag();
                        break;
                    case 2:
                        tag = new AuthorTag();
                        break;
                    default:
                        System.out.println("init tag error");
                        return;
                }

                tag.setId(resultSet.getLong("id"));
                tag.setName(resultSet.getString("name"));
                tag.setType(type);

                switch (type) {
                    case 0:
                        List<IsbnTag> isbn = ebooks.get(resultSet.getLong("eid")).getIsbn();
                        if (isbn == null) {
                            isbn = new ArrayList<>();
                        }
                        isbn.add((IsbnTag) tag);
                        ebooks.get(resultSet.getLong("eid")).setIsbn(isbn);
                        break;
                    case 1:
                        ebooks.get(resultSet.getLong("eid")).setPublisher((PublisherTag) tag);
                        break;
                    case 2:
                        ebooks.get(resultSet.getLong("eid")).setAuthor((AuthorTag) tag);
                        break;
                    default:
                        System.out.println("set ebook tag error");
                        return;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void resultSet2Attachment(Map<Long, Ebook> ebooks, ResultSet resultSet) {
        if (resultSet == null) {
            System.out.println("tag resultSet is null");
            return;
        }
        Attachment attachment;

        try {
            while (resultSet.next()) {
                int type = resultSet.getInt("type");
                switch (type) {
                    case 0:
                        attachment = new FileAttachment();
                        break;
                    case 1:
                        attachment = new ImageAttachment();
                        break;
                    default:
                        System.out.println("init attachment class error");
                        return;
                }

                attachment.setId(resultSet.getLong("id"));
                attachment.setAid(resultSet.getString("aid"));
                attachment.setName(resultSet.getString("name"));
                attachment.setSize(resultSet.getDouble("size"));
                attachment.setSizeUnit(resultSet.getString("sizeUnit"));
                attachment.setExtension(resultSet.getString("extension"));
                attachment.setFilePath(resultSet.getString("filePath"));

                long eid = resultSet.getLong("parent");
                switch (type) {
                    case 0:
                        ebooks.get(eid).setFile((FileAttachment) attachment);
                        break;
                    case 1:
                        ebooks.get(eid).setImage((ImageAttachment) attachment);
                        break;
                    default:
                        System.out.println("set ebook attachment error");
                        return;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public EbookVo selectById(long eid) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = PortalTool.connection();
            if (connection == null) {
                System.out.println("get mysql connection is empty");
                return null;
            }

            String select = "select * from ebook where id = ?";
            statement = connection.prepareStatement(select);
            statement.setLong(1, eid);
            ResultSet resultSet = statement.executeQuery();

            EbookVo ebookVo = new EbookVo();

            if (resultSet.next()) {
                ebookVo.setBookName(resultSet.getString("book_name"));
                ebookVo.setYear(resultSet.getString("year"));
                ebookVo.setLanguage(resultSet.getString("language"));
                ebookVo.setId(resultSet.getLong("id"));
            }

            String selectTag = "select t.id, t.name, t.type, t.status, t.sort, t.updateTime, t.createTime from ebook_tag et left join tag t on et.tid = t.id where et.eid = ?";
            statement = connection.prepareStatement(selectTag);
            statement.setLong(1, ebookVo.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int type = resultSet.getInt("type");
                switch (type) {
                    case 0:
                        break;
                    case 1:
                        ebookVo.setPublisher(resultSet.getString("name"));
                        break;
                    case 2:
                        ebookVo.setAuthor(resultSet.getString("name"));
                        break;
                    default:
                        System.out.println("can not parse tag");
                }
            }

            String selectAttachment = "select * from attachment where parent = ?";
            statement = connection.prepareStatement(selectAttachment);
            statement.setLong(1, ebookVo.getId());
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int type = resultSet.getInt("type");
                switch (type) {
                    case 0:
                        ebookVo.setSize(resultSet.getDouble("size") + "");
                        ebookVo.setSizeUnit(resultSet.getString("sizeUnit"));
                        ebookVo.setExtension(resultSet.getString("extension"));
                        break;
                    case 1:
                        ebookVo.setImageId(resultSet.getLong("id"));
                        break;
                    default:
                }
            }

            return ebookVo;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            PortalTool.close(connection, statement);
        }
    }

    private void setTag(ResultSet resultSet, Tag tag) throws SQLException {
        tag.setId(resultSet.getLong("id"));
        tag.setName(resultSet.getString("name"));
        tag.setType(resultSet.getInt("type"));
    }

    public ImageAttachment selectAttachmentById(String imgId) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = PortalTool.connection();
            String select = "select * from attachment where id = ?";
            statement = connection.prepareStatement(select);
            statement.setInt(1, Integer.parseInt(imgId));
            ResultSet resultSet = statement.executeQuery();
            ImageAttachment imageAttachment = null;
            if (resultSet.next()) {
                imageAttachment = new ImageAttachment();
                imageAttachment.setFilePath(resultSet.getString("filePath"));
                imageAttachment.setExtension(resultSet.getString("extension"));
            }
            return imageAttachment;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            PortalTool.close(connection, statement);
        }
        return null;
    }
}
