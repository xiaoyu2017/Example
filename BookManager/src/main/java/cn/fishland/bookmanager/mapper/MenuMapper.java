package cn.fishland.bookmanager.mapper;

import cn.fishland.bookmanager.bean.pojo.Menu;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

}
