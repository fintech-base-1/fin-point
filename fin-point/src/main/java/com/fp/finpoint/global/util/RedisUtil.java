package com.fp.finpoint.global.util;

import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import io.lettuce.core.RedisCommandExecutionException;
import io.lettuce.core.RedisConnectionException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisUtil {

    private final RedisTemplate<String, String> redisTemplate;
    public ValueOperations<String, String> getValueOperations() {
        try {
            return redisTemplate.opsForValue();
        } catch (RedisConnectionException e) {
            throw new BusinessLogicException(ExceptionCode.REDIS_CONNECTION_ERROR);
        }
    }

    public String getRedisValue(ValueOperations<String, String> operations, String key) {
        try {
            return operations.get(key);
        } catch (RedisCommandExecutionException e) {
            throw new BusinessLogicException(ExceptionCode.REDIS_COMMAND_ERROR);
        }
    }

    public void setRedisValue(ValueOperations<String, String> operations, String key, String value, int timeout, TimeUnit timeUnit) {
        try {
            operations.set(key, value, timeout, timeUnit);
        } catch (RedisCommandExecutionException e) {
            throw new BusinessLogicException(ExceptionCode.REDIS_COMMAND_ERROR);
        }
    }
}
