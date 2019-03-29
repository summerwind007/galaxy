package io.fighter.galaxy.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author xiasx
 * @create 2019-03-14 16:01
 **/

@Repository
public class RedisDao {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOperations;

    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOperations;

    private static final String DEFAULT_PREFIX = "public:redis:";


    public void valueSet(String key, String value) {
        valueOperations.set(DEFAULT_PREFIX + key, value);
    }

    public void valueSet(String key, String value, long exp) {
        valueOperations.set(DEFAULT_PREFIX + key, value, exp, TimeUnit.SECONDS);
    }

    public String valueGet(String key) {
        return valueOperations.get(DEFAULT_PREFIX + key);
    }

    public boolean exists(String key) {
        return redisTemplate.hasKey(DEFAULT_PREFIX + key);
    }

    public void delete(String key) {
        redisTemplate.delete(DEFAULT_PREFIX + key);
    }

    public void delete(List<String> keys) {
        if (CollectionUtils.isEmpty(keys)) {
            return;
        }
        redisTemplate.delete(keys);
    }

    public Long increment(String key) {
        return valueOperations.increment(DEFAULT_PREFIX + key, 1L);
    }

    public Long increment(String key, long step) {
        return valueOperations.increment(DEFAULT_PREFIX + key, step);
    }

    public void leftPush(String key, String value) {
        listOperations.leftPush(DEFAULT_PREFIX + key, value);
    }

    public void rightPush(String key, String value) {
        listOperations.rightPush(DEFAULT_PREFIX + key, value);
    }

    public String leftPop(String key) {
        return listOperations.leftPop(DEFAULT_PREFIX + key);
    }

    public String rightPop(String key) {
        return listOperations.rightPop(DEFAULT_PREFIX + key);
    }
}
