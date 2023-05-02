package com.example.diploma;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest
@EnableAutoConfiguration
@Disabled
class DiplomaApplicationTests {

    static {
        System.setProperty("spring.datasource.url", "jdbc:mysql://localhost:3306/test");
    }

    @Test
    void contextLoads() {
    }

}
