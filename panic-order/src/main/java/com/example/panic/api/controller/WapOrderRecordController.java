package com.example.panic.api.controller;

import com.example.panic.domain.order.domainService.OrderRecordService;
import constant.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 订单记录表
 *
 * @author zhangshaobo
 * @email zhangshaobo@liangbaba.com
 * @date 2024-02-07 18:18:23
 * @version V0.1
 */
@RestController
@RequestMapping("wap/orderRecord")
public class WapOrderRecordController {
    @Autowired
    private OrderRecordService orderRecordService;

    /**
     * 下单接口
     * @return
     */
    @PostMapping("/createOrder")
    public CommonResult createOrder(){
        return CommonResult.success(orderRecordService.createOrder());
    }

}
