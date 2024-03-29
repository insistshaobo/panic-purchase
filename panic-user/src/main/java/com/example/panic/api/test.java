package com.example.panic.api;

import constant.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangshaobo
 * @version 8.2
 * @Description: TODO
 * @date 2023/12/2 18:19
 */
@RestController
@Slf4j
@RequestMapping("/test")
public class test {

    @Autowired
    private OrderApi orderApi;

    @GetMapping("/test")
    public CommonResult test() {
        log.info("=========用户下单成功===========");
        orderApi.test();
        return CommonResult.success(1);
    }
}
