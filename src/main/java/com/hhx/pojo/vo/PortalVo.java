package com.hhx.pojo.vo;

import lombok.Data;

/**
 * @Author: hhx
 * @Date: 2024/5/15 15:41
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class PortalVo {
    private String keyWords;
    private int type=0;
    private int pageNum=1;
    private int pageSize=10;
}
