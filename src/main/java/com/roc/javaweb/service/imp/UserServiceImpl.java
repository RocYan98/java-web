package com.roc.javaweb.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roc.javaweb.domain.User;
import com.roc.javaweb.service.UserService;
import com.roc.javaweb.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String username, String pwd) {
        User one = getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username).eq(User::getPwd, pwd));
        return one;
    }

    @Override
    public User getByUid(String uid) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getUid, uid));
    }

    @Override
    public User getByUsername(String username) {
        return getOne(new LambdaQueryWrapper<User>().eq(User::getUsername, username));
    }

    @Override
    public void updatePwd(String uid, String pwd) {
        User user = new User();
        user.setPwd(pwd);
        update(user, new LambdaUpdateWrapper<User>().eq(User::getUid, uid));
    }

    @Override
    public void register(User user) {
        userMapper.inserUser(user.getUid(), user.getUsername(), user.getPwd());
    }

    @Override
    public void updateHead(String uid, String url) {
        User user = new User();
        user.setHead(url);
        update(user, new LambdaUpdateWrapper<User>().eq(User::getUid, uid));
    }
}
