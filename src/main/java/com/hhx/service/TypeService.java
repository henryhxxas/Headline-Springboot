package com.hhx.service;

import com.hhx.pojo.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhx.utils.Result;

/**
* @author Lenovo
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-05-15 10:29:41
*/
public interface TypeService extends IService<Type> {

    /**
     * 查询所有类别数据
     * @return
     */
    Result findAllTypes();
}
