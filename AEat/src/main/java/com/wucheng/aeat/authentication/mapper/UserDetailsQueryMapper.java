package com.wucheng.aeat.authentication.mapper;

import com.wucheng.aeat.authentication.domain.dto.UserAuthenticationDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserDetailsQueryMapper {
    @Select("select id,user_name,password from user where id=#{id}")
    UserAuthenticationDTO getUser(Long id);
    @Select("select id,user_name,password from user where user_name=#{userName}")
    UserAuthenticationDTO getUserByName(String userName);
}
