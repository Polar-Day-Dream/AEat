package com.wucheng.aeat.authentication.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.wucheng.aeat.authentication.config.JwtProperties;
import com.wucheng.aeat.authentication.domain.dto.UserAuthenticationDTO;
import com.wucheng.aeat.authentication.domain.entity.UserDetailItem;
import com.wucheng.aeat.authentication.service.UserDetailsQueryService;
import com.wucheng.aeat.authentication.service.impl.UserDetailsServiceImpl;
import com.wucheng.aeat.authentication.utils.JwtTool;
import com.wucheng.aeat.domain.dto.UserDTO;
import com.wucheng.aeat.domain.entity.R;
import com.wucheng.aeat.exeception.UnauthorizedException;
import com.wucheng.aeat.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@Slf4j
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JwtTool jwtTool;
    private final JwtProperties jwtProperties;
    private final StringRedisTemplate stringRedisTemplate;
    private final UserDetailsQueryService userDetailsQueryService;

    //自定义登录接口
    @PostMapping("/login")

    public R<String> login(@RequestBody UserAuthenticationDTO userDTO){
        log.info("登录验证");
        // 使用的Authentication其实是UsernamePasswordAuthenticationToken
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDTO.getUserName(),userDTO.getPassword());
        // 只有认证通过才会返回Authentication，其他会抛出异常
        Authentication authenticate = null;
        try {
            authenticate = authenticationManager.authenticate(authentication);
        } catch (AuthenticationException e) {
            return R.error(new UnauthorizedException("用户名或者密码错误"));
        }

        //在security上下文中获取到user
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        UserDetailItem user = (UserDetailItem) authenticate.getPrincipal();
        System.out.println(user+"------------------------------------------------");
        if(user == null){
            throw new UnauthorizedException("没有用户信息");
        }


        //TODO:获取用户权限..............
        //生成token
        String token=jwtTool.createToken(user.getId(),jwtProperties.getTokenTTL());
        //存redis
        UserDTO userDTO1 = new UserDTO(user.getId(),user.getUsername());
        Map<String, Object> userInfo = BeanUtil.beanToMap(userDTO1,new HashMap<>(),
                CopyOptions.create()
                        .setIgnoreNullValue(true)
                        .setFieldValueEditor((filedName,filedValue) -> filedValue.toString())
        );
        stringRedisTemplate.opsForHash().putAll(token,userInfo);
        stringRedisTemplate.expire(token,jwtProperties.getTokenTTL());
        log.info("{}登录成功，生成token：{} ",userDTO,token);

        return R.ok(token);
    }

}
