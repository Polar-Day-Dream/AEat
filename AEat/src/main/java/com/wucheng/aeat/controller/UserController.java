package com.wucheng.aeat.controller;

import com.wucheng.aeat.domain.dto.UserDTO;
import com.wucheng.aeat.domain.entity.R;
import com.wucheng.aeat.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;
    private final StringRedisTemplate stringRedisTemplate;

    @PostMapping("/{id}")
    public R<UserDTO> getUser(@PathVariable Long id){
        UserDTO user=userService.getUser(id);
        return R.ok(user);
    }
    @PostMapping("/insert")
    public R insertRedis(){
        stringRedisTemplate.opsForValue().set("name","wangwu");

        return R.ok();
    }


}
