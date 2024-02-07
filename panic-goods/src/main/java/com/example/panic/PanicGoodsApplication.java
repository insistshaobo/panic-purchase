package com.example.panic;

import config.UniqueNameGenerator;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;


@Slf4j
@ComponentScan(nameGenerator = UniqueNameGenerator.class)
@EnableFeignClients
@SpringBootApplication
@MapperScan("com.example.panic.domain.**.dao")
@EnableAsync
@EnableRetry
@EnableAspectJAutoProxy(exposeProxy = true)
public class PanicGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanicGoodsApplication.class, args);
        log.info("================= PanicGoodsApplication 启动成功=========================");
    }

}
