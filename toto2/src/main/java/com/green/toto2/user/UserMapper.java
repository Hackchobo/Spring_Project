package com.green.toto2.user;

import com.green.toto2.user.model.UserInsDto;
import com.green.toto2.user.model.UserLoginDto;
import com.green.toto2.user.model.UserLoginVo;
import com.green.toto2.user.model.UserPatchPwDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int insUser(UserInsDto dto);
    UserLoginVo loginUser(UserLoginDto dto);
    int updUserPw(UserPatchPwDto dto);
}
