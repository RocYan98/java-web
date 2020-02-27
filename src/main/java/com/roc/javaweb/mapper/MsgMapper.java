package com.roc.javaweb.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.roc.javaweb.domain.Msg;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MsgMapper extends BaseMapper<Msg> {
}
