package com.roc.javaweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roc.javaweb.domain.Food;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FoodMapper extends BaseMapper<Food> {
}
