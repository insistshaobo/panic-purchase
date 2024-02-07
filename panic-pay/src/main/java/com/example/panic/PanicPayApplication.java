package com.example.panic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class PanicPayApplication {

    public static void main(String[] args) {
        SpringApplication.run(PanicPayApplication.class, args);
        log.info("================= PanicPayApplication 启动成功=========================");
    }

}
