package com.wucheng.aeat.authentication.service.impl;


import com.wucheng.aeat.authentication.domain.dto.UserAuthenticationDTO;
import com.wucheng.aeat.authentication.domain.entity.UserDetailItem;
import com.wucheng.aeat.authentication.mapper.UserDetailsQueryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserDetailsQueryMapper userDetailsQueryMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuthenticationDTO userDTO = userDetailsQueryMapper.getUserByName(username);
        if(username.equals(userDTO.getUserName())){
            UserDetailItem userDetailItem = new UserDetailItem();
            userDetailItem.setUserDTO(userDTO);
            return userDetailItem;
        }
        throw new UsernameNotFoundException("验证不通过");
    }
}
