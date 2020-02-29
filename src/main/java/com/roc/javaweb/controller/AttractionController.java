package com.roc.javaweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.roc.javaweb.domain.Attractions;
import com.roc.javaweb.domain.Food;
import com.roc.javaweb.service.AttractionService;
import com.roc.javaweb.util.OSSClientUtil;
import com.roc.javaweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attr")
public class AttractionController {

    @Autowired
    private AttractionService attractionService;

    @Autowired
    private OSSClientUtil ossClientUtil;

    @CrossOrigin
    @GetMapping("/page")
    public Result queryPage(int current, int size, String search) {
        IPage<Attractions> page = attractionService.getPage(current, size, search);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", page.getTotal());
        hashMap.put("records", page.getRecords());
        return new Result<Map>(0, hashMap);
    }

    @CrossOrigin
    @GetMapping("/info")
    public Result queryByUid(String uid) {
        List<Attractions> list = attractionService.list(new LambdaQueryWrapper<Attractions>().eq(Attractions::getUid, uid));
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("attractions", list);
        return new Result<Map>(0, hashMap);
    }

    @CrossOrigin
    @PostMapping("/update")
    public Result update(MultipartFile file, int aid, String uid, String title, String text) {
        Attractions attractions = new Attractions();
        attractions.setAid(aid);
        attractions.setUid(uid);
        attractions.setText(text);
        attractions.setTitle(title);
        if (file != null) {
            String fileName = ossClientUtil.uploadImg2Oss(file);
            if (fileName.equals("上传失败")) return new Result<Attractions>(-1, "上传失败");
            attractions.setPic("http://oss.rocyan.com/javaweb/" + fileName);
        }

        try {
            attractionService.updateById(attractions);
        } catch (Exception e) {
            return new Result<Food>(-1, "没有此学号，更新失败");
        }

        return new Result<Food>(0, "修改成功");
    }

    @CrossOrigin
    @PostMapping("/add")
    public Result<Attractions> add(MultipartFile file, int aid, String uid, String title, String text) {
        Attractions attractions = new Attractions();
        attractions.setAid(aid);
        attractions.setUid(uid);
        attractions.setText(text);
        attractions.setTitle(title);
        if (file != null) {
            String fileName = ossClientUtil.uploadImg2Oss(file);
            if (fileName.equals("上传失败")) return new Result<Attractions>(-1, "上传失败");
            attractions.setPic("http://oss.rocyan.com/javaweb/" + fileName);
        } else {
            attractions.setPic("http://oss.rocyan.com/javaweb/1582902816521.png");
        }

        try {
            attractionService.save(attractions);
        } catch (Exception e) {
            return new Result<Attractions>(-1, "没有此学号，添加失败");
        }
        return new Result<Attractions>(0, "添加成功");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    public Result<Attractions> deleteOne(@RequestBody Attractions attractions) {
        attractionService.removeById(attractions);
        return new Result<Attractions>(0, "删除成功");
    }

    @CrossOrigin
    @PostMapping("/deleteSelected")
    public Result<Attractions> deleteSelected(@RequestBody Long[] idList) {
        Arrays.stream(idList).forEach(id -> attractionService.removeById(id));
        return new Result<Attractions>(0, "删除成功");
    }
}
