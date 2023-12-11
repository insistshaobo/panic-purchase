package com.example.ticketorder;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TicketMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketMessageApplication.class, args);
        log.info("================= TicketMessageApplication 启动成功=========================");
    }

}
