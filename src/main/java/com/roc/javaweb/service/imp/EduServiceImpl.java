package com.roc.javaweb.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roc.javaweb.domain.Edu;
import com.roc.javaweb.mapper.EduMapper;
import com.roc.javaweb.service.EduService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EduServiceImpl extends ServiceImpl<EduMapper, Edu> implements EduService {

    @Autowired
    private EduMapper eduMapper;

    @Override
    public List<Edu> getByUid(String uid) {
        return list(new LambdaQueryWrapper<Edu>().eq(Edu::getUid, uid));
    }

    @Override
    public void registerUser(String uid) {
        Edu edu = new Edu();
        edu.setUid(uid);
        edu.setTitle("未设置");
        edu.setPos("未设置");
        edu.setWeb("未设置");
        edu.setName("primary");
        edu.setSta("小学");
        save(edu);
        edu.setName("middle");
        edu.setSta("初中");
        save(edu);
        edu.setName("high");
        edu.setSta("高中");
        save(edu);
        edu.setName("university");
        edu.setSta("大学");
        save(edu);
    }
}
