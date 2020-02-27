package com.roc.javaweb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.mysql.cj.util.StringUtils;
import com.roc.javaweb.domain.Msg;
import com.roc.javaweb.service.MsgService;
import com.roc.javaweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/msg")
public class MsgController {

    @Autowired
    private MsgService msgService;

    @CrossOrigin
    @GetMapping("/page")
    public Result queryPage(int current) {
        IPage<Msg> page = msgService.getPage(current, 3);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", page.getTotal());
        hashMap.put("records", page.getRecords());
        return new Result<Map>(0, hashMap);
    }

    @CrossOrigin
    @PostMapping("/input")
    public Result input(@RequestBody Msg msg) {
        if (StringUtils.isNullOrEmpty(msg.getName())) {
            msg.setName("游客");
        }
        msgService.save(msg);
        return new Result(0);
    }
}
