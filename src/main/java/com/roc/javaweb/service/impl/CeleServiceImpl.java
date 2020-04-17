package com.roc.javaweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.roc.javaweb.domain.Cele;
import com.roc.javaweb.mapper.CeleMapper;
import com.roc.javaweb.service.CeleService;
import org.springframework.stereotype.Service;

@Service
public class CeleServiceImpl extends ServiceImpl<CeleMapper, Cele> implements CeleService {
    @Override
    public IPage<Cele> getPage(int current, int size, String search) {
        IPage<Cele> page = null;
        if (!StringUtils.isNullOrEmpty(search)) {
            page = page(new Page<Cele>(current, size).setAsc("uid"), new LambdaQueryWrapper<Cele>().like(Cele::getUid, search).or().like(Cele::getTitle, search));
        } else page = page(new Page<Cele>(current, size).setAsc("uid"));
        return page;
    }
}
