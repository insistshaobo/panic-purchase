package com.example.panic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PanicMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanicMessageApplication.class, args);
        log.info("================= PanicMessageApplication 启动成功=========================");
    }

}
