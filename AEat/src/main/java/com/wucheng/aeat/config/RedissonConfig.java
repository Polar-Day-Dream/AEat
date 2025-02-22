package com.wucheng.aeat.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
@Lazy
public class RedissonConfig {
    @Bean
    public RedissonClient redissonClient(){
        //配置
        Config config=new Config();
        //创建RedissonClient对象
        config.useSingleServer().setAddress("redis://127.0.0.1:6379").setPassword("123456");
        return Redisson.create(config);
    }


}
