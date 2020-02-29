package com.roc.javaweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mysql.cj.util.StringUtils;
import com.roc.javaweb.domain.Food;
import com.roc.javaweb.mapper.FoodMapper;
import com.roc.javaweb.service.FoodService;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl extends ServiceImpl<FoodMapper, Food> implements FoodService {
    @Override
    public IPage<Food> getPage(int current, int size, String search) {
        IPage<Food> page = null;
        if (!StringUtils.isNullOrEmpty(search)) {
            page = page(new Page<Food>(current, size).setAsc("uid"), new LambdaQueryWrapper<Food>().like(Food::getUid, search).or().like(Food::getTitle, search));
        } else page = page(new Page<Food>(current, size).setAsc("uid"));
        return page;
    }
}
