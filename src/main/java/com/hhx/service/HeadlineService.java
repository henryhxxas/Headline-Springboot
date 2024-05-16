package com.hhx.service;

import com.hhx.pojo.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hhx.pojo.vo.PortalVo;
import com.hhx.utils.Result;

/**
* @author Lenovo
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-05-15 10:29:41
*/
public interface HeadlineService extends IService<Headline> {

    /**
     * 首页数据查询
     * @param portalVo
     * @return
     */
    Result findNewsPage(PortalVo portalVo);

    /**
     * 根据id查询头条详情
     * @param hid
     * @return
     */
    Result showHeadlineDetail(Integer hid);

    /**
     * 发布头条方法
     * @param headline
     * @return
     */
    Result publish(Headline headline,String token);

    /**
     * 根据id查询头条详细信息
     * @param hid
     * @return
     */
    Result findHeadlineByHid(Integer hid);

    /**
     * 修改头条数据
     * @param headline
     * @return
     */
    Result updateData(Headline headline);
}
