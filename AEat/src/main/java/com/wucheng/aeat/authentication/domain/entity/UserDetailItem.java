package com.wucheng.aeat.authentication.domain.entity;

import com.wucheng.aeat.authentication.domain.dto.UserAuthenticationDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
public class UserDetailItem implements UserDetails {

    private UserAuthenticationDTO userDTO;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
    public Long getId(){
        return userDTO.getId();
    }
    @Override
    public String getPassword() {
        return userDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return userDTO.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
