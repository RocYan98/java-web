package com.roc.javaweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roc.javaweb.domain.Edu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EduMapper extends BaseMapper<Edu> {
    List<Edu> selectByUid(String uid);
}
