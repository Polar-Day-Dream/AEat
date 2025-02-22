package com.wucheng.aeat.authentication.service.impl;

import com.wucheng.aeat.authentication.domain.dto.UserAuthenticationDTO;
import com.wucheng.aeat.authentication.mapper.UserDetailsQueryMapper;
import com.wucheng.aeat.authentication.service.UserDetailsQueryService;
import com.wucheng.aeat.domain.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsQueryServiceImpl implements UserDetailsQueryService {
    private final UserDetailsQueryMapper userDetailsQueryMapper;
    @Override
    public UserAuthenticationDTO getUser(Long id) {
        return userDetailsQueryMapper.getUser(id);
    }

    @Override
    public UserAuthenticationDTO getUserByName(String name) {
        UserAuthenticationDTO user = userDetailsQueryMapper.getUserByName(name);
        return user;
    }

}
