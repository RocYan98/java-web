package com.roc.javaweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roc.javaweb.domain.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    void inserUser(String uid, String username, String pwd);
}
