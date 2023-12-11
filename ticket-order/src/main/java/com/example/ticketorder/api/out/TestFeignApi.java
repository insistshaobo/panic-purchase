package com.example.ticketorder.api.out;

import constant.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhangshaobo
 * @version 8.2
 * @Description: TODO
 * @date 2023/12/2 18:19
 */

@FeignClient(name = "ticket-order",path = "ticket-order/rpc/test")
public interface TestFeignApi {

     @GetMapping("/test")
     CommonResult test() ;
}
