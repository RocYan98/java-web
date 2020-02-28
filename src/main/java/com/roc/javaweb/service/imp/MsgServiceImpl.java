package com.roc.javaweb.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.roc.javaweb.domain.Msg;
import com.roc.javaweb.mapper.MsgMapper;
import com.roc.javaweb.service.MsgService;
import org.springframework.stereotype.Service;

@Service
public class MsgServiceImpl extends ServiceImpl<MsgMapper, Msg> implements MsgService {
    @Override
    public IPage<Msg> getPage(int current, int size, String search) {
        IPage<Msg> page = null;
        if (!StringUtils.isNullOrEmpty(search)) {
            page = page(new Page<Msg>(current, size).setDesc("time"), new LambdaQueryWrapper<Msg>().like(Msg::getName, search).or().like(Msg::getMsg, search));
        } else page = page(new Page<Msg>(current, size).setDesc("time"));
        return page;
    }
}
