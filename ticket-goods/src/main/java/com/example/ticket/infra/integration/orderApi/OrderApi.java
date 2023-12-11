package com.example.ticket.infra.integration.orderApi;

import constant.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhangshaobo
 * @version 8.2
 * @Description: TODO
 * @date 2023/12/2 19:03
 */
@FeignClient(name = "ticket-order",path = "ticket-order/rpc/test")
public interface OrderApi {

    @GetMapping("/test")
    CommonResult test();
}

