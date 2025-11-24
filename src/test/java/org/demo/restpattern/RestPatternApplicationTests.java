package org.demo.restpattern;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("Requires MongoDB and Kafka to be running")
class RestPatternApplicationTests {

    @Test
    void contextLoads() {
    }

}
