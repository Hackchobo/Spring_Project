package com.green.babyfood1.User;

import com.green.babyfood1.User.model.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/api/user")
@Tag(name = "유저/관리자 회원관리")
public class UserController {

    private final UserService service;

    @PostMapping("/create/user")
    @Operation(summary = "유저/관리자 회원가입",description = ""+
            "iuser:회원의 PK값(몇번째 등록된 사람인지)<br>" +
            "goal: 목표를 적으시오"
    )
    public int postUser(@RequestBody UserInsDto dto){
        return service.insUser(dto);
    }

    @PostMapping("/create/admin")
    public int postAdmin(@RequestBody AdminInsDto dto){
        return service.insAdmin(dto);
    }

    @GetMapping("/seach")
    public List<UserEntity> getUser(){
        return service.selUser();
    }

    @PutMapping("/pix")
    public int patchUser(@RequestBody UserUpdDto dto){
        return service.updUser(dto);
    }

    @DeleteMapping("/{iuser}")
    public int delUser(@PathVariable Long iuser){
        UserDelDto dto = new UserDelDto();
        dto.setIuser(iuser);
        return service.delUser(dto);
    }
}
