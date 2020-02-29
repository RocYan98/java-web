package com.roc.javaweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.roc.javaweb.domain.Food;
import com.roc.javaweb.service.FoodService;
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
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private OSSClientUtil ossClientUtil;

    @CrossOrigin
    @GetMapping("/page")
    public Result queryPage(int current, int size, String search) {
        IPage<Food> page = foodService.getPage(current, size, search);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", page.getTotal());
        hashMap.put("records", page.getRecords());
        return new Result<Map>(0, hashMap);
    }

    @CrossOrigin
    @GetMapping("/info")
    public Result queryByUid(String uid) {
        List<Food> list = foodService.list(new LambdaQueryWrapper<Food>().eq(Food::getUid, uid));
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("food", list);
        return new Result<Map>(0, hashMap);
    }

    @CrossOrigin
    @PostMapping("/update")
    public Result update(MultipartFile file, int fid, String uid, String title, String text) {
        Food food = new Food();
        food.setFid(fid);
        food.setUid(uid);
        food.setText(text);
        food.setTitle(title);
        if (file != null) {
            String fileName = ossClientUtil.uploadImg2Oss(file);
            if (fileName.equals("上传失败")) return new Result<Food>(-1, "上传失败");
            food.setPic("http://oss.rocyan.com/javaweb/" + fileName);
        }

        try {
            foodService.updateById(food);
        } catch (Exception e) {
            return new Result<Food>(-1, "没有此学号，更新失败");
        }

        return new Result<Food>(0, "修改成功");
    }

    @CrossOrigin
    @PostMapping("/add")
    public Result<Food> add(MultipartFile file, int fid, String uid, String title, String text) {
        Food food = new Food();
        food.setFid(fid);
        food.setUid(uid);
        food.setText(text);
        food.setTitle(title);
        if (file != null) {
            String fileName = ossClientUtil.uploadImg2Oss(file);
            if (fileName.equals("上传失败")) return new Result<Food>(-1, "上传失败");
            food.setPic("http://oss.rocyan.com/javaweb/" + fileName);
        } else {
            food.setPic("http://oss.rocyan.com/javaweb/1582902816521.png");
        }

        try {
            foodService.save(food);
        } catch (Exception e) {
            return new Result<Food>(-1, "没有此学号，添加失败");
        }
        return new Result<Food>(0, "添加成功");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    public Result<Food> deleteOne(@RequestBody Food food) {
        foodService.removeById(food);
        return new Result<Food>(0, "删除成功");
    }

    @CrossOrigin
    @PostMapping("/deleteSelected")
    public Result<Food> deleteSelected(@RequestBody Long[] idList) {
        Arrays.stream(idList).forEach(id -> foodService.removeById(id));
        return new Result<Food>(0, "删除成功");
    }
}
