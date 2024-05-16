package com.hhx.controller;

import com.hhx.pojo.User;
import com.hhx.service.UserService;
import com.hhx.utils.JwtHelper;
import com.hhx.utils.Result;
import com.hhx.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: hhx
 * @Date: 2024/5/15 11:05
 * @Description: TODO
 * @Version: 1.0
 */
@RestController
@RequestMapping("user")
@CrossOrigin
public class UserController {
    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result=userService.login(user);
        System.out.println("result="+result);
        return result;
    }

    @GetMapping("getUserInfo")
    public Result getUserInfo(@RequestHeader String token){
        Result result=userService.getUserInfo(token);
        return result;
    }

    @PostMapping("checkUserName")
    public Result checkUserName(String username){
        Result result=userService.checkUserName(username);
        return result;
    }

    @PostMapping("regist")
    public Result register(@RequestBody User user){
        Result result=userService.register(user);
        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        boolean expiration = jwtHelper.isExpiration(token);
        if(expiration){
            //已经过期
            return Result.build(null, ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }
}
