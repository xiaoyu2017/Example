package cn.fishland.bookmanager.mapper;

import cn.fishland.bookmanager.bean.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 用户Mapper
 *
 * @author xiaoyu
 * @version 1.0
 */
public interface UserMapper {

    List<User> selectAll();

    User selectById(int id);

    User selectByCompanyAndSex(@Param("companyName") String companyName, @Param("sex") Byte sex);

    User selectByNameAndPassword(@Param("name") String name, @Param("password") String password);

}
