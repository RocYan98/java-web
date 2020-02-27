package com.roc.javaweb.controller;

import com.roc.javaweb.domain.Edu;
import com.roc.javaweb.service.EduService;
import com.roc.javaweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
