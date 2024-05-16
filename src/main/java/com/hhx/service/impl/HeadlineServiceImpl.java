package com.hhx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hhx.pojo.Headline;
import com.hhx.pojo.vo.PortalVo;
import com.hhx.service.HeadlineService;
import com.hhx.mapper.HeadlineMapper;
import com.hhx.utils.JwtHelper;
import com.hhx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Lenovo
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-05-15 10:29:41
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private JwtHelper jwtHelper;

    @Autowired
    private HeadlineMapper headlineMapper;

    /**
     * 首页数据查询
     *
     * 1.进行分页数据查询
     * 2.分页数据，拼接到result即可
     *
     * 注意1：查询需要自定义语句 自定义mapper的方法 携带分页
     * 注意2：返回的结果List<Map>
     * @param portalVo
     * @return
     */
    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Map> page=new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectMyPage(page,portalVo);
        List<Map> records = page.getRecords();
        Map data=new HashMap<>();
        data.put("pageData",records);
        data.put("pageNum",page.getCurrent());
        data.put("pageSize",page.getSize());
        data.put("totalPage",page.getPages());
        data.put("totalSize",page.getTotal());
        Map pageInfo=new HashMap();
        pageInfo.put("pageInfo",data);
        return Result.ok(pageInfo);
    }

    /**
     * 根据id查询头条详情
     *
     * 1.查询对应的数据【多表，头条，用户表；方法需要自定义 返回map即可】
     * 2.修改阅读量 +1 【version乐观锁，当前数据对应的版本】
     * @param hid
     * @return
     */
    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map data=headlineMapper.queryDetailMap(hid);
        Map headlineMap=new HashMap();
        headlineMap.put("headline",data);
        //修改阅读量+1
        Headline headline = new Headline();
        headline.setHid((Integer) data.get("hid"));
        headline.setVersion((Integer) data.get("version"));
        headline.setPageViews((Integer) data.get("pageViews")+1);
        headlineMapper.updateById(headline);
        return Result.ok(headlineMap);
    }

    /**
     * 发布头条方法
     *
     * 1.补全数据
     * @param headline
     * @return
     */
    @Override
    public Result publish(Headline headline,String token) {
        //token查询用户id
        int userId = jwtHelper.getUserId(token).intValue();
        //数据装配
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headlineMapper.insert(headline);
        return Result.ok(null);
    }

    @Override
    public Result findHeadlineByHid(Integer hid) {
        Headline headline = headlineMapper.selectById(hid);
        Map data=new HashMap();
        data.put("headline",headline);
        return Result.ok(data);
    }

    /**
     * 修改头条数据
     *
     * 1.hid查询数据的最新version
     * 2.修改数据的修改时间为当前节点
     * @param headline
     * @return
     */
    @Override
    public Result updateData(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);//乐观锁
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.ok(null);
    }
}




