package com.sandyz.bobtimerserver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication(scanBasePackages = "com.sandyz.bobtimerserver")
@MapperScan("com.sandyz.bobtimerserver.mapper")
public class BobTimerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BobTimerServerApplication.class, args);
    }


}
