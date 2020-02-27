package com.roc.javaweb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.roc.javaweb.domain.Edu;

import java.util.List;

public interface EduService extends IService<Edu> {
    List<Edu> getByUid(String uid);

    void registerUser(String uid);
}
