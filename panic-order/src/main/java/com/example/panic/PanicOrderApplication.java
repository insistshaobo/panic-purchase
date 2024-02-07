package com.example.panic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@Slf4j
@EnableFeignClients
@EnableAspectJAutoProxy(exposeProxy = true)
public class PanicOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanicOrderApplication.class, args);
        log.info("================= PanicOrderApplication 启动成功=========================");
    }

}
