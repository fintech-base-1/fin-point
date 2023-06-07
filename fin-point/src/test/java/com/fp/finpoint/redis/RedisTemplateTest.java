package com.fp.finpoint.redis;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Map;
import java.util.Set;

@SpringBootTest
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    void testString() {
        // given
        ValueOperations<String, String> valueOperations = redisTemplate.opsForValue();
        String key = "stringKey";

        // when
        valueOperations.set(key,"hello");

        // then
        String value = valueOperations.get(key);
        Assertions.assertThat(value).isEqualTo("hello");
    }

    @Test
    void testSet() {
        // given
        SetOperations<String, String> setOperations = redisTemplate.opsForSet();
        String key = "setKey";

        // when
        setOperations.add(key, "h", "e", "l", "l", "o");

        // then
        Set<String> members = setOperations.members(key);
        Long size = setOperations.size(key);

        Assertions.assertThat(members).containsOnly("h", "e", "l", "o");
        Assertions.assertThat(size).isEqualTo(4);
    }

    @Test
    void testHash() {
        // given
        HashOperations<String, Object, Object> hashOperations = redisTemplate.opsForHash();
        String key = "hashKey";

        // when
        hashOperations.put(key, "hello", "world");

        // then
        Object value = hashOperations.get(key, "hello");
        Assertions.assertThat(value).isEqualTo("world");

        Map<Object, Object> entries = hashOperations.entries(key);
        Assertions.assertThat(entries.keySet()).containsExactly("hello");
        Assertions.assertThat(entries.values()).containsExactly("world");

        Long size = hashOperations.size(key);
        Assertions.assertThat(size).isEqualTo(entries.size());
    }

}
