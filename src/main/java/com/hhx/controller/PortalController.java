package com.hhx.controller;

import com.hhx.pojo.vo.PortalVo;
import com.hhx.service.HeadlineService;
import com.hhx.service.TypeService;
import com.hhx.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: hhx
 * @Date: 2024/5/15 15:31
 * @Description: TODO 首页的控制层
 * @Version: 1.0
 */
@RestController
@RequestMapping("portal")
@CrossOrigin
public class PortalController {
    @Autowired
    private TypeService typeService;

    @Autowired
    private HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result result=typeService.findAllTypes();
        return result;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result result=headlineService.findNewsPage(portalVo);
        return result;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result=headlineService.showHeadlineDetail(hid);
        return result;
    }

}
