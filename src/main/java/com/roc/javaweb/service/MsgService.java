package com.roc.javaweb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roc.javaweb.domain.Msg;

public interface MsgService extends IService<Msg> {
    IPage<Msg> getPage(int current, int size);
}
