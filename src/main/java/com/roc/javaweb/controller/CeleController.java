package com.roc.javaweb.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.roc.javaweb.domain.Cele;
import com.roc.javaweb.domain.Food;
import com.roc.javaweb.service.CeleService;
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
@RequestMapping("/api/cele")
public class CeleController {

    @Autowired
    private CeleService celeService;

    @Autowired
    private OSSClientUtil ossClientUtil;

    @CrossOrigin
    @GetMapping("/page")
    public Result queryPage(int current, int size, String search) {
        IPage<Cele> page = celeService.getPage(current, size, search);
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("total", page.getTotal());
        hashMap.put("records", page.getRecords());
        return new Result<Map>(0, hashMap);
    }

    @CrossOrigin
    @GetMapping("/info")
    public Result queryByUid(String uid) {
        List<Cele> list = celeService.list(new LambdaQueryWrapper<Cele>().eq(Cele::getUid, uid));
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("cele", list);
        return new Result<Map>(0, hashMap);
    }

    @CrossOrigin
    @PostMapping("/update")
    public Result update(MultipartFile file, int cid, String uid, String title, String text) {
        Cele cele = new Cele();
        cele.setCid(cid);
        cele.setUid(uid);
        cele.setText(text);
        cele.setTitle(title);
        if (file != null) {
            String fileName = ossClientUtil.uploadImg2Oss(file);
            if (fileName.equals("上传失败")) return new Result<Food>(-1, "上传失败");
            cele.setPic("http://oss.rocyan.com/javaweb/" + fileName);
        }

        try {
            celeService.updateById(cele);
        } catch (Exception e) {
            return new Result<Food>(-1, "没有此学号，更新失败");
        }

        return new Result<Food>(0, "修改成功");
    }

    @CrossOrigin
    @PostMapping("/add")
    public Result<Cele> add(MultipartFile file, int cid, String uid, String title, String text) {
        Cele cele = new Cele();
        cele.setCid(cid);
        cele.setUid(uid);
        cele.setText(text);
        cele.setTitle(title);
        if (file != null) {
            String fileName = ossClientUtil.uploadImg2Oss(file);
            if (fileName.equals("上传失败")) return new Result<Cele>(-1, "上传失败");
            cele.setPic("http://oss.rocyan.com/javaweb/" + fileName);
        } else {
            cele.setPic("http://oss.rocyan.com/javaweb/1582902816521.png");
        }

        try {
            celeService.save(cele);
        } catch (Exception e) {
            return new Result<Cele>(-1, "没有此学号，添加失败");
        }
        return new Result<Cele>(0, "添加成功");
    }

    @CrossOrigin
    @PostMapping("/deleteOne")
    public Result<Cele> deleteOne(@RequestBody Cele cele) {
        celeService.removeById(cele);
        return new Result<Cele>(0, "删除成功");
    }

    @CrossOrigin
    @PostMapping("/deleteSelected")
    public Result<Cele> deleteSelected(@RequestBody Long[] idList) {
        Arrays.stream(idList).forEach(id -> celeService.removeById(id));
        return new Result<Cele>(0, "删除成功");
    }
}
