package com.example.panic.api.web.wap;

import com.example.panic.domain.product.domainService.GoodsSpuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 商品spu信息表
 *
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 11:04:01
 * @version V0.1
 */
@RestController
@RequestMapping("wap/goodsSpu")
public class WapGoodsSpuController {
    @Autowired
    private GoodsSpuService goodsSpuService;

}
