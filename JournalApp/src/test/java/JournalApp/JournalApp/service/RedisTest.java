package JournalApp.JournalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    @Disabled
    void testSendMail() {
        redisTemplate.opsForValue().set("email", "kunjkhimani13@gmail.com");
        Object salary = redisTemplate.opsForValue().get("salary");

        int a = 1;

    }
}
