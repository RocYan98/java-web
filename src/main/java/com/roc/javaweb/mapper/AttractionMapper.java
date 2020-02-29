package com.roc.javaweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roc.javaweb.domain.Attractions;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AttractionMapper extends BaseMapper<Attractions> {
}
