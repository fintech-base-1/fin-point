package com.fp.finpoint.global.util;

import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import io.lettuce.core.RedisCommandExecutionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Slf4j
@Component
public class RedisUtil {

    private static StringRedisTemplate stringRedisTemplate;

    @Autowired
    public RedisUtil(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public static String getRedisValue(String key) {
        try {
            return stringRedisTemplate.opsForValue().get(key);
        } catch (RedisCommandExecutionException e) {
            throw new BusinessLogicException(ExceptionCode.REDIS_COMMAND_ERROR);
        }
    }

    public static void setRedisValue(String key, String value, int timeout, TimeUnit timeUnit) {
        try {
            stringRedisTemplate.opsForValue().set(key, value, timeout, timeUnit);
        } catch (RedisCommandExecutionException e) {
            throw new BusinessLogicException(ExceptionCode.REDIS_COMMAND_ERROR);
        }
    }

    public static void deleteKey(String key) {
        try {
            stringRedisTemplate.delete(key);
        } catch (RedisCommandExecutionException e) {
            throw new BusinessLogicException(ExceptionCode.REDIS_COMMAND_ERROR);
        }
    }
}
