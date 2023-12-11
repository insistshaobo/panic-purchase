package com.example.ticket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@Slf4j
@EnableFeignClients
@EnableAspectJAutoProxy(exposeProxy = true)
public class TicketGoodsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketGoodsApplication.class, args);
        log.info("================= TicketGoodsApplication 启动成功=========================");
    }

}
