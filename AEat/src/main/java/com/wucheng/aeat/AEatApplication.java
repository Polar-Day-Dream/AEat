package com.wucheng.aeat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootApplication
@MapperScan("com.wucheng.aeat.**.mapper")
public class AEatApplication {
    public static void main(String[] args) {
        SpringApplication.run(AEatApplication.class, args);

    }


}
