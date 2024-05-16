package com.hhx.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhx.pojo.User;
import com.hhx.service.UserService;
import com.hhx.mapper.UserMapper;
import com.hhx.utils.JwtHelper;
import com.hhx.utils.MD5Util;
import com.hhx.utils.Result;
import com.hhx.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author Lenovo
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-05-15 10:29:41
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtHelper jwtHelper;

    /**
     * 登录业务
     *  1，根据账号查询用户对象 -loginUser
     *  2，如果用户对象为null，查询失败 账号错误！~501
     *  3，对比密码 失败 返回503错误
     *  4，根据用户id生成一个token，token->result 返回
     * @param user
     * @return
     */
    @Override
    public Result login(User user) {
        //根据账号查询数据
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);
        if (loginUser==null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        //对比密码
        if(!StringUtils.isEmpty(user.getUserPwd())&& MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
            //登录成功
            //根据用户id生成token
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            //将token封装到result返回
            Map data = new HashMap();
            data.put("token",token);
            return Result.ok(data);
        }

        //密码错误
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }


    /**
     * 根据token获取用户数据
     *
     * 1.token是否有效
     * 2.根据token解析userId
     * 3.根据用户id查询用户数据
     * 4.去掉密码，封装result结果返回即可
     * @param token
     * @return
     */
    @Override
    public Result getUserInfo(String token) {
        //是否过期 true过期
        boolean expiration = jwtHelper.isExpiration(token);
        if(expiration){
            //失效 未登录看待
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        int userId = jwtHelper.getUserId(token).intValue();
        User user = userMapper.selectById(userId);
        user.setUserPwd("");
        Map data=new HashMap();
        data.put("loginUser",user);
        return Result.ok(data);
    }

    /**
     * 检查账号是否可用
     * 1.根据账号进行count查询
     * 2.count=0 可用
     * 3.count>0 不可用
     * @param username
     * @return
     */
    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,username);
        Long count = userMapper.selectCount(lambdaQueryWrapper);
        if(count==0){
            return Result.ok(null);
        }
        return Result.build(null,ResultCodeEnum.USERNAME_USED);
    }

    /**
     * 注册业务
     * 1.依然检查账号是否已经被注册
     * 2.密码加密处理
     * 3.账号数据保存
     * 4.返回结果
     * @param user
     * @return
     */
    @Override
    public Result register(User user) {
        LambdaQueryWrapper<User> queryWrapper=new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername,user.getUsername());
        Long count = userMapper.selectCount(queryWrapper);
        if(count>0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        user.setUserPwd(MD5Util.encrypt(user.getUserPwd()));
        userMapper.insert(user);
        return Result.ok(null);
    }
}




