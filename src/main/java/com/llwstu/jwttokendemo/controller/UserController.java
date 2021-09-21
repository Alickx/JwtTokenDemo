package com.llwstu.jwttokendemo.controller;


import com.llwstu.jwttokendemo.entity.User;
import com.llwstu.jwttokendemo.util.JwtUtil;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {


    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {

        Map<String, Object> map = new HashMap<>();
        String username = user.getUsername();
        String password = user.getPassword();
        // 省略 账号密码验证
        // 验证成功后发送token
        String token = JwtUtil.sign(username, password);
        if (token != null) {
            map.put("code", "200");
            map.put("message", "认证成功");
            map.put("token", token);
            return map;
        }
        map.put("code", "403");
        map.put("message", "认证失败");
        return map;
    }

    @GetMapping(value = "/api/test")
    public String get(){
        System.out.println("执行了get请求");
        return "success";
    }
}

