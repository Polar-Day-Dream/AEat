package com.wucheng.aeat.authentication.service;

import com.wucheng.aeat.authentication.domain.dto.UserAuthenticationDTO;
import com.wucheng.aeat.domain.dto.UserDTO;

public interface UserDetailsQueryService {
    UserAuthenticationDTO getUser(Long id);
    UserAuthenticationDTO getUserByName(String name);
}
