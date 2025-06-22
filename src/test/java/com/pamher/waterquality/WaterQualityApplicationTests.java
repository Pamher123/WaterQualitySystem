package com.pamher.waterquality;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.pamher.waterquality.mapper")
class WaterQualityApplicationTests {

    @Test
    void contextLoads() {
    }

}
