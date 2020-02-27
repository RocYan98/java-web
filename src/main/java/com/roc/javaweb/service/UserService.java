package com.roc.javaweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.roc.javaweb.domain.User;

public interface UserService extends IService<User> {
    User login(String username, String pwd);

    User getByUid(String uid);

    User getByUsername(String username);

    void updatePwd(String uid, String pwd);

    void register(User user);

    void updateHead(String uid, String url);
}
