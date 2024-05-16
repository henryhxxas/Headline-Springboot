package com.hhx.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.hhx.pojo.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hhx.pojo.vo.PortalVo;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author Lenovo
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-05-15 10:29:41
* @Entity com.hhx.pojo.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {
    IPage<Map> selectMyPage(IPage page,@Param("portalVo") PortalVo portalVo);

    Map queryDetailMap(Integer hid);
}




