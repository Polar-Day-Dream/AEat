package com.wucheng.aeat.service;


import com.wucheng.aeat.domain.dto.UserDTO;

public interface UserService {
    UserDTO getUser(Long id);
}
