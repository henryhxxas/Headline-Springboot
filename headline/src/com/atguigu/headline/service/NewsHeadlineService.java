package com.atguigu.headline.service;

import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;

import java.util.List;
import java.util.Map;

public interface NewsHeadlineService {
    List<NewsHeadline> findAll();

    Map<String, Object> findPage(HeadlineQueryVo headLineQueryVo);

    HeadlineDetailVo findHeadlineDetail(Integer hid);

    int increasePageViews(Integer hid);

    int addNewsHeadline(NewsHeadline newsHeadline);

    NewsHeadline findHeadlineByHid(Integer hid);

    int updateNewsHeadline(NewsHeadline newsHeadline);

    int removeByHid(Integer hid);
}
