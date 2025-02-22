package com.wucheng.aeat.authentication.config;


import com.wucheng.aeat.authentication.exception.AuthenticationExceptionEntryPoint;
import com.wucheng.aeat.authentication.filter.JWTTokenAuthFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Slf4j
@RequiredArgsConstructor
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JWTTokenAuthFilter jwtTokenAuthFilter;
    @Bean
    public BCryptPasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 这里配置SpringSecurity的信息
        http
                .cors()//启用cors
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(new AuthenticationExceptionEntryPoint()) // 自定义认证异常处理
                .and()
                .csrf().disable()// 禁用csrf
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//不使用session
                .and() // 重新返回一个HttpSecurityBuilder
                .authorizeRequests() // 配置接口的拦截
                .antMatchers("/login").anonymous()// 放行接口
                .anyRequest().authenticated();// 拦截接口
        // 添加JWT过滤器-将其放在认证之前即可
        http.addFilterBefore(jwtTokenAuthFilter, UsernamePasswordAuthenticationFilter.class);
    }



}
