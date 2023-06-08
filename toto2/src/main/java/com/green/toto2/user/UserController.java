package com.green.toto2.user;

import com.green.toto2.user.model.UserInsDto;
import com.green.toto2.user.model.UserLoginDto;
import com.green.toto2.user.model.UserPatchPwDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "유저", description = "")
public class UserController {

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public int postUser(@RequestBody UserInsDto dto){
        return service.insUser(dto);
    }

    @PostMapping("/login")
    public int postLoginUser(@RequestBody UserLoginDto dto){
        return service.login(dto);
    }

    @PatchMapping("/pw")
    public int patchPwUser(@RequestBody UserPatchPwDto dto){
        return service.updUserPw(dto);
    }
}
