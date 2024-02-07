package com.example.panic.api.web.wap;

import com.example.panic.domain.product.domainService.GoodsSkuService;
import constant.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 商品sku信息表
 *
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 11:04:01
 * @version V0.1
 */
@RestController
@RequestMapping("/wap/goodsSku")
public class WapGoodsSkuController {
    @Autowired
    private GoodsSkuService goodsSkuService;

    @GetMapping("/queryAll")
    public CommonResult queryAll(){
        return CommonResult.success(goodsSkuService.queryAll());
    }

}
