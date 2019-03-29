package io.fighter.galaxy.cache.config;

import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author xiasx
 * @create 2019-03-14 16:02
 **/

@Configuration
@AutoConfigureAfter(RedisAutoConfiguration.class)
public class RedisConfig {

    @Bean
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);
        template.setValueSerializer(getRedisSerializer());
        template.setKeySerializer(getRedisSerializer());
        return template;
    }


    /**
     * 声明序列化工具(String)
     * @return
     */
    @Bean
    public StringRedisSerializer getRedisSerializer(){
        return new StringRedisSerializer();
    }


    /**
     * 声明序列化工具(JDK)
     * @return
     */
    @Bean
    public JdkSerializationRedisSerializer getDefauldSerializer(){
        return new JdkSerializationRedisSerializer();
    }
}
