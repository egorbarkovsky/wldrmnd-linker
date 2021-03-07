package com.wldrmnd.smlr

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.TestPropertySource

@TestPropertySource(locations = ["classpath:repositories-test.properties"])
@SpringBootTest
class SmlrApplicationTests {

    @Test
    fun contextLoads() {
    }

}
