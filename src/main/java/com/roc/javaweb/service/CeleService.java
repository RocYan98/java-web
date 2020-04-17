package com.roc.javaweb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roc.javaweb.domain.Cele;

public interface CeleService extends IService<Cele> {
    IPage<Cele> getPage(int current, int size, String search);
}
