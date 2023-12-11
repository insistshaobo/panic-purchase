package com.example.ticketorder.api.out.impl;

import com.example.ticketorder.api.out.TestFeignApi;
import constant.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangshaobo
 * @version 8.2
 * @Description: TODO
 * @date 2023/12/2 18:19
 */

@Slf4j
@RestController
@RequestMapping("/rpc/test")
public class TestFeignClient implements TestFeignApi {

    @Override
    @GetMapping("/test")
    public CommonResult test() {
        log.info("=========用户生成订单成功===========");
        return CommonResult.success(1);
    }
}
