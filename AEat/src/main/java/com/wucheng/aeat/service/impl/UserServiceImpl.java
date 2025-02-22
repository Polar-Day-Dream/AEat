package com.wucheng.aeat.service.impl;


import com.wucheng.aeat.domain.dto.UserDTO;
import com.wucheng.aeat.mapper.UserMapper;
import com.wucheng.aeat.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    @Override
    public UserDTO getUser(Long id) {
        return userMapper.getUser(id);
    }
}


