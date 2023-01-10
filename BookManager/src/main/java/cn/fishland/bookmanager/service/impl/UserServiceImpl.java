package cn.fishland.bookmanager.service.impl;

import cn.fishland.bookmanager.bean.pojo.User;
import cn.fishland.bookmanager.mapper.UserMapper;
import cn.fishland.bookmanager.service.UserService;
import cn.fishland.bookmanager.tool.WebTool;

import java.io.IOException;

/**
 * 用户服务类实现类
 *
 * @author xiaoyu
 * @version 1.0
 */
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    @Override
    public User login(User user) {
        try {
            userMapper = (UserMapper) WebTool.getMapper(UserMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return userMapper.selectByNameAndPassword(user.getName(), user.getPassword());
    }
}
