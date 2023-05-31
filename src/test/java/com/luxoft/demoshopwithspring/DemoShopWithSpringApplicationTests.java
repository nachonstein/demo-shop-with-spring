package com.luxoft.demoshopwithspring;

import com.luxoft.demoshopwithspring.configuration.KafkaProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class DemoShopWithSpringApplicationTests {

    @Autowired
    private KafkaProperties kafkaProperties;

    @Test
    void contextLoads() {

    }

}
