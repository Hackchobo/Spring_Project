package com.green.zero.user;

import com.green.zero.user.model.UserInsDto;
import com.green.zero.user.model.UserLoginDto;
import com.green.zero.user.model.UserUpdPw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public int PostUser(@RequestBody UserInsDto dto) {
        return service.insUser(dto);
    }

    @PostMapping("/login")
    public int loginUser(UserLoginDto dto){
        return service.login(dto);
    }

    @PatchMapping("/pw")
    public int Patch(UserUpdPw pw){
        return service.updUserPw(pw);
    }
}
