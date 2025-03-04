package com.sandyz.bobtimerserver.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.PolymorphicTypeValidator;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

        @Bean
        public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
                RedisTemplate<String, Object> template = new RedisTemplate<>();
                template.setConnectionFactory(redisConnectionFactory);

                // 创建 ObjectMapper 实例
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);

                // 使用 activateDefaultTyping 替代 enableDefaultTyping
                PolymorphicTypeValidator ptv = LaissezFaireSubTypeValidator.instance;
                objectMapper.activateDefaultTyping(ptv, ObjectMapper.DefaultTyping.NON_FINAL);

                // 使用 Jackson2JsonRedisSerializer 并传入 ObjectMapper 配置
                Jackson2JsonRedisSerializer<Object> jacksonSerializer = new Jackson2JsonRedisSerializer<>(objectMapper, Object.class);

                // 设置 key 和 value 的序列化规则
                template.setKeySerializer(new StringRedisSerializer()); // key 使用 String 序列化器
                template.setValueSerializer(jacksonSerializer);         // value 使用 Jackson 序列化器

                // 对 hash 的 key 和 value 设置序列化规则
                template.setHashKeySerializer(new StringRedisSerializer());
                template.setHashValueSerializer(jacksonSerializer);

                template.afterPropertiesSet();
                return template;
        }
}
