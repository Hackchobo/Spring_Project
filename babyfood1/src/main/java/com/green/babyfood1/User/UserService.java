package com.green.babyfood1.User;

import com.green.babyfood1.User.model.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class UserService {

    private final UserMapper mapper;

    public int insUser(UserInsDto dto){
        return mapper.insUser(dto);
    }

    public int insAdmin(AdminInsDto dto){
        return mapper.insAdmin(dto);
    }

    public List<UserEntity> selUser(){
        return mapper.selUser();
    }

    public int updUser(UserUpdDto dto){
        return mapper.updUser(dto);
    }

    public int delUser(UserDelDto dto){
        return mapper.delUser(dto);
    }
}
