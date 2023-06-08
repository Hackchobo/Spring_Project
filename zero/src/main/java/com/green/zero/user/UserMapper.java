package com.green.zero.user;

import com.green.zero.user.model.UserInsDto;
import com.green.zero.user.model.UserLoginDto;
import com.green.zero.user.model.UserLoginVo;
import com.green.zero.user.model.UserUpdPw;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserInsDto dto);
    UserLoginVo loginUser(UserLoginDto dto);
    int updUserPw(UserUpdPw pw);
}
