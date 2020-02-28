package com.roc.javaweb.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.roc.javaweb.domain.Edu;
import com.roc.javaweb.domain.Msg;
import com.roc.javaweb.service.EduService;
import com.roc.javaweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/edu")
public class EduController {
    @Autowired
    private EduService eduService;

    @CrossOrigin
    @PostMapping("/update")
    public Result updateEdu(@RequestBody Edu edu) {
        Edu byId = eduService.getById(edu.getEid());
        if (byId.getTitle().equals(edu.getTitle()) && byId.getPos().equals(edu.getPos()) && byId.getWeb().equals(edu.getWeb())) {
            return new Result(-1);
        }
        eduService.updateById(edu);
        Edu res = eduService.getById(edu.getEid());
        return new Result(0, "修改成功", res);
    }

    @CrossOrigin
    @GetMapping("/page")
    public Result queryPage(int current, int size, String search) {
        IPage<Edu> page = eduService.getPage(current, size, search);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", page.getTotal());
        hashMap.put("records", page.getRecords());
        return new Result<Map>(0, hashMap);
    }

    @CrossOrigin
    @PostMapping("/update")
    public Result<Msg> update(@RequestBody Edu edu) {
        eduService.updateById(edu);
        return new Result<Msg>(0, "修改成功");
    }
}
