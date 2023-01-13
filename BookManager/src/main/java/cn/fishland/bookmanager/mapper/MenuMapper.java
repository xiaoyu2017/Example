package cn.fishland.bookmanager.mapper;

import cn.fishland.bookmanager.bean.pojo.Menu;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 菜单Mapper
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface MenuMapper {

    @Insert("insert into menu(`name`, `icon`, `link`, `parent`, `sort`, `status`) " +
            "value (#{name}, #{icon}, #{link}, #{parent}, #{sort}, #{status});")
    int insert(Menu menu);

    @Insert("delete from menu where id = #{id}")
    int delete(@Param("id") long id);

    @Update("update menu set name = #{name}, icon = #{icon}, link = #{link}, sort = #{sort} where id = #{id}")
    int update(Menu menu);

    @Select("select * from menu where id = #{id}")
    Menu select(@Param("id") long id);

    @Select("select * from menu where parent = #{id}")
    List<Menu> selectAllByParent(@Param("id") long id);

    /**
     * 一对一
     *
     * @param id menu id
     * @return Menu实例对象
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "createTime", column = "createTime"),
            @Result(property = "updateTime", column = "updateTime"),
            @Result(property = "status", column = "status"),
            @Result(property = "sort", column = "sort"),
            @Result(property = "name", column = "name"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "link", column = "link"),
            @Result(property = "parentMenu", column = "parent", javaType = Menu.class,
                    one = @One(select = "cn.fishland.bookmanager.mapper.MenuMapper.select")),
    })
    @Select("select * from menu where id = #{id}")
    Menu selectParentById(@Param("id") Long id);

    /**
     * 一对多
     *
     * @param id menu id
     * @return Menu实例对象
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "createTime", column = "createTime"),
            @Result(property = "updateTime", column = "updateTime"),
            @Result(property = "status", column = "status"),
            @Result(property = "sort", column = "sort"),
            @Result(property = "name", column = "name"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "link", column = "link"),
            @Result(property = "children", column = "id", javaType = List.class,
                    many = @Many(select = "cn.fishland.bookmanager.mapper.MenuMapper.selectAllByParent")),
    })
    @Select("select * from menu where id = #{id}")
    Menu selectChildById(@Param("id") Long id);

    /**
     * 多对多
     *
     * @return Menu实例对象
     */
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "createTime", column = "createTime"),
            @Result(property = "updateTime", column = "updateTime"),
            @Result(property = "status", column = "status"),
            @Result(property = "sort", column = "sort"),
            @Result(property = "name", column = "name"),
            @Result(property = "icon", column = "icon"),
            @Result(property = "link", column = "link"),
            @Result(property = "children", column = "id", javaType = List.class,
                    many = @Many(select = "cn.fishland.bookmanager.mapper.MenuMapper.selectAllByParent")),
    })
    @Select("select * from menu")
    List<Menu> selectAll();
}
