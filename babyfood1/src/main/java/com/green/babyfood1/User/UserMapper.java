package com.green.babyfood1.User;

import com.green.babyfood1.User.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    int insUser(UserInsDto dto);
    int insAdmin(AdminInsDto dto);
    List<UserEntity> selUser();
    int updUser(UserUpdDto dto);
    int delUser(UserDelDto dto);
}
