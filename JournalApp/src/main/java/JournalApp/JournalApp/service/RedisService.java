package JournalApp.JournalApp.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
public class RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public <T> T get(String key, Class<T> entityClass) {
        try {
            String json = redisTemplate.opsForValue().get(key);
            if (json == null) {
                return null; // cache miss
            }
            return objectMapper.readValue(json, entityClass);
        } catch (Exception e) {
            log.error("Exception reading from Redis key: " + key, e);
            return null;
        }
    }

    public void set(String key, Object o, Long ttlSeconds) {
        try {
            String jsonValue = objectMapper.writeValueAsString(o);
            log.info("Storing in Redis -> key: {}, value: {}", key, jsonValue); // ✅ LOG IT
            redisTemplate.opsForValue().set(key, jsonValue, ttlSeconds, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Exception writing to Redis key: " + key, e);
        }
    }

}
