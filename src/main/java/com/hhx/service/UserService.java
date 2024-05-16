package com.hhx.service;

import com.hhx.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhx.utils.Result;

/**
* @author Lenovo
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-05-15 10:29:41
*/
public interface UserService extends IService<User> {

    /**
     * 登录业务
     * @return
     */
    Result login(User user);

    /**
     * 根据token获取用户数据
     * @param token
     * @return
     */
    Result getUserInfo(String token);

    /**
     * 检查账号是否可用
     * @param username
     * @return
     */
    Result checkUserName(String username);

    /**
     * 注册业务
     * @param user
     * @return
     */
    Result register(User user);
}
