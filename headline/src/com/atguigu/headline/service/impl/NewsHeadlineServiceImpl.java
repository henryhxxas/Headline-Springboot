package com.atguigu.headline.service.impl;

import com.atguigu.headline.dao.NewsHeadLineDao;
import com.atguigu.headline.dao.impl.NewsHeadlineDaoImpl;
import com.atguigu.headline.pojo.NewsHeadline;
import com.atguigu.headline.pojo.vo.HeadlineDetailVo;
import com.atguigu.headline.pojo.vo.HeadlinePageVo;
import com.atguigu.headline.pojo.vo.HeadlineQueryVo;
import com.atguigu.headline.service.NewsHeadlineService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NewsHeadlineServiceImpl  implements NewsHeadlineService {
    private NewsHeadLineDao newsHeadLineDao =new NewsHeadlineDaoImpl();
    @Override
    public List<NewsHeadline> findAll() {

        return newsHeadLineDao.findAll();
    }

    @Override
    public Map<String, Object> findPage(HeadlineQueryVo headLineQueryVo) {
        Map<String,Object> pageInfo =new HashMap<>();
        List<HeadlinePageVo>  pageData =newsHeadLineDao.findPageList(headLineQueryVo);
        int totalSize = newsHeadLineDao.findPageCount(headLineQueryVo);
        int pageSize =headLineQueryVo.getPageSize();
        int totalPage=totalSize%pageSize == 0 ?  totalSize/pageSize  : totalSize/pageSize+1;
        int pageNum= headLineQueryVo.getPageNum();
        pageInfo.put("pageData",pageData);
        pageInfo.put("pageNum",pageNum);
        pageInfo.put("pageSize",pageSize);
        pageInfo.put("totalPage",totalPage);
        pageInfo.put("totalSize",totalSize);


        return pageInfo;
    }

    @Override
    public HeadlineDetailVo findHeadlineDetail(Integer hid) {
        return newsHeadLineDao.findHeadlineDetail(hid);
    }

    @Override
    public int increasePageViews(Integer hid) {
        return newsHeadLineDao.increasePageViews(hid);
    }

    @Override
    public int addNewsHeadline(NewsHeadline newsHeadline) {
        return newsHeadLineDao.addNewsHeadline(newsHeadline);
    }

    @Override
    public NewsHeadline findHeadlineByHid(Integer hid) {
        return newsHeadLineDao.findHeadlineByHid(hid);
    }

    @Override
    public int updateNewsHeadline(NewsHeadline newsHeadline) {
        return newsHeadLineDao.updateNewsHeadline(newsHeadline);
    }

    @Override
    public int removeByHid(Integer hid) {
        return newsHeadLineDao.removeByHid(hid);
    }
}
