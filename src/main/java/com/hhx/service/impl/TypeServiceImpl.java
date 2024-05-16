package com.hhx.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhx.pojo.Type;
import com.hhx.service.TypeService;
import com.hhx.mapper.TypeMapper;
import com.hhx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* @author Lenovo
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-05-15 10:29:41
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    private TypeMapper typeMapper;
    /**
     * 查询所有类别数据
     * @return
     */
    @Override
    public Result findAllTypes() {
        List<Type> types = typeMapper.selectList(null);
        return Result.ok(types);
    }
}




