package com.roc.javaweb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roc.javaweb.domain.Food;

public interface FoodService extends IService<Food> {
    IPage<Food> getPage(int current, int size, String search);
}
