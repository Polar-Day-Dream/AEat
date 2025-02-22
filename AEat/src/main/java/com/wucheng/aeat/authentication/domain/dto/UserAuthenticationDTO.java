package com.wucheng.aeat.authentication.domain.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserAuthenticationDTO {
    private Long id;
    private String userName;
    private String password;
}
