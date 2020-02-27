package com.roc.javaweb.controller;

import com.roc.javaweb.domain.User;
import com.roc.javaweb.service.UserService;
import com.roc.javaweb.util.OSSClientUtil;
import com.roc.javaweb.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private OSSClientUtil ossClientUtil;

    @CrossOrigin
    @PostMapping("/update")
    public Result<User> updateUser(@RequestBody User requestUser) {
        User oldUser = userService.getByUid(requestUser.getUid());
        if (oldUser.getCname().equals(requestUser.getCname()) && oldUser.getEname().equals(requestUser.getEname()) && oldUser.getEmail().equals(requestUser.getEmail()) && oldUser.getCls().equals(requestUser.getCls())) {
            return new Result<>(-1);
        }
        userService.updateById(requestUser);
        User responseUser = userService.getByUid(requestUser.getUid());
        return new Result<User>(0, "修改成功", responseUser);
    }

    @CrossOrigin
    @GetMapping("/updatePwd")
    public Result<User> updatePwd(String uid, String oldPwd, String pwd) {
        User user = userService.getByUid(uid);
        if (!user.getPwd().equals(oldPwd)) return new Result<User>(-1, "原密码错误");
        userService.updatePwd(uid, pwd);

        return new Result<User>(0, "修改成功");
    }

    @CrossOrigin
    @PostMapping("/updateHead")
    public Result<String> updateHead(MultipartFile file) {
        String fileName = ossClientUtil.uploadImg2Oss(file);
        if (fileName.equals("上传失败")) return new Result<>(-1, "上传失败", "上传失败");

        String url = "http://oss.rocyan.com/javaweb/" + fileName;
        return new Result<>(0, "上传成功", url);
    }

    @CrossOrigin
    @GetMapping("/updateDB")
    public Result<User> updateDB(String url, String uid) {
        userService.updateHead(uid, url);
        return new Result<User>(0, "上传成功", userService.getByUid(uid));
    }
}
