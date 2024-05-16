package com.atguigu.headline.dao;

import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.NewsUser;

import java.util.List;

public interface NewsUserDao {


    NewsUser findByUserName(String username);

    int insertNewsUser(NewsUser newsUser);

    NewsUser findByUid(Integer uid);
}
