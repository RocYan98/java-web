package com.roc.javaweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.roc.javaweb.domain.Attractions;
import com.roc.javaweb.mapper.AttractionMapper;
import com.roc.javaweb.service.AttractionService;
import org.springframework.stereotype.Service;

@Service
public class AttractionServiceImpl extends ServiceImpl<AttractionMapper, Attractions> implements AttractionService {
    @Override
    public IPage<Attractions> getPage(int current, int size, String search) {
        IPage<Attractions> page = null;
        if (!StringUtils.isNullOrEmpty(search)) {
            page = page(new Page<Attractions>(current, size).setAsc("uid"), new LambdaQueryWrapper<Attractions>().like(Attractions::getUid, search).or().like(Attractions::getTitle, search));
        } else page = page(new Page<Attractions>(current, size).setAsc("uid"));
        return page;
    }
}
