package com.roc.javaweb.service.imp;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.roc.javaweb.domain.Msg;
import com.roc.javaweb.mapper.MsgMapper;
import com.roc.javaweb.service.MsgService;
import org.springframework.stereotype.Service;

@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements MsgService {
    @Override
    public IPage<Msg> getPage(int current, int size) {
        IPage<Msg> page = page(new Page<Msg>(current, size).setDesc("time"));
        return page;
    }
}
