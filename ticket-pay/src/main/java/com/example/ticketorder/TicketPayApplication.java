package com.example.ticketorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TicketPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketPayApplication.class, args);
        log.info("================= TicketPayApplication 启动成功=========================");
    }

}
