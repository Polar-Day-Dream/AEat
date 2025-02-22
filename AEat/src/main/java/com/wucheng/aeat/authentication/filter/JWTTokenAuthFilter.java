package com.wucheng.aeat.authentication.filter;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.wucheng.aeat.authentication.domain.dto.UserAuthenticationDTO;
import com.wucheng.aeat.authentication.mapper.UserDetailsQueryMapper;
import com.wucheng.aeat.authentication.utils.JwtTool;
import com.wucheng.aeat.exeception.UnauthorizedException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@RequiredArgsConstructor
@Component
public class JWTTokenAuthFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsQueryMapper userDetailsQueryMapper;
    @Autowired
    private JwtTool jwtTool;
    private final StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * 1.获取请求头中的token,无Token直接放行
         * 2.验证token是否有效，无效也放行
         * 3.有效将用户id存入Authentication中，并交给SecurityContextHoler
         * 4.继续向后执行
         */
        String authorization = request.getHeader("Authorization");

        // 校验 Token
        if (!StrUtil.isEmpty(authorization)) {
            String token = authorization;
            try {
                // 解析 Token 并获取用户 ID
                String id = stringRedisTemplate.opsForValue().get(token);
                if (!StrUtil.isEmpty(id)) {

                    // 根据用户 ID 获取用户信息
                    UserAuthenticationDTO user = userDetailsQueryMapper.getUser(Long.valueOf(id));
                    if (!ObjectUtil.isEmpty(user)) {
                        // 设置认证信息到 SecurityContext
                        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUserName(),user.getPassword(),null);
                        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                    }
                }
            } catch (UnauthorizedException e) {
                // 记录异常日志，或自定义返回错误信息
                logger.info("JWT Token 验证失败: {}",e);
            }
        }

        // 继续请求处理
        filterChain.doFilter(request, response);
    }


}
