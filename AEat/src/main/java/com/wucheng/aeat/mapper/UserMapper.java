package com.wucheng.aeat.mapper;

import com.wucheng.aeat.domain.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select id,user_name,password from user where id=#{id}")
    UserDTO getUser(Long id);
}
