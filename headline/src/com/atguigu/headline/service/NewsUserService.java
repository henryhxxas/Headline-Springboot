package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsUser;

public interface NewsUserService {
    NewsUser findByUserName(String username);

    int registUser(NewsUser newsUser);

    NewsUser findByUid(Integer uid);
}
