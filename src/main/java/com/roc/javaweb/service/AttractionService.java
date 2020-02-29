package com.roc.javaweb.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.roc.javaweb.domain.Attractions;

public interface AttractionService extends IService<Attractions> {
    IPage<Attractions> getPage(int current, int size, String search);
}
