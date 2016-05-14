package org.ceeker.web.sbootm.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.ceeker.web.sbootm.entity.User;

@Mapper
public interface UserMapper {
//    @Select("SELECT * FROM user WHERE username = #{username}")
    User findByUserName(@Param("username") String username);
    
}
