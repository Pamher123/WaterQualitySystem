package com.pamher.waterquality;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pamher.waterquality.mapper")
public class WaterQualityApplication {

    public static void main(String[] args) {
        SpringApplication.run(WaterQualityApplication.class, args);
    }

}
