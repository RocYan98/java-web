package com.roc.javaweb.controller;

import com.roc.javaweb.domain.User;
import com.roc.javaweb.service.EduService;
import com.roc.javaweb.util.Result;
import com.roc.javaweb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private EduService eduService;

    @CrossOrigin
    @PostMapping("/login")
    public Result login(@RequestBody User requestUser) {
        // 对 html 标签进行转义，防止 XSS 攻击
        String username = requestUser.getUsername();
        String pwd = requestUser.getPwd();
        User selectUser = userService.login(username, pwd);
        if (selectUser == null) {
            String message = "用户名或密码错误";
            return new Result<User>(-1, message);
        } else {
            HashMap<String, Object> res = new HashMap<>();
            res.put("user", selectUser);
            res.put("eduList", eduService.getByUid(selectUser.getUid()));
            return new Result<Map>(0, res);
        }
    }

    @CrossOrigin
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user) {
        if (userService.getByUid(user.getUid()) != null) {
            return new Result<User>(-1, "学号已注册");
        }

        if (userService.getByUsername(user.getUsername()) != null) {
            return new Result<User>(-1, "用户名已存在");
        }
        userService.register(user);
        eduService.registerUser(user.getUid());
        return new Result<User>(0, "注册成功");
    }

}
