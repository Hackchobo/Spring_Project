package com.green.zero.user;

import com.green.zero.user.model.UserInsDto;
import com.green.zero.user.model.UserLoginDto;
import com.green.zero.user.model.UserLoginVo;
import com.green.zero.user.model.UserUpdPw;
import com.green.zero.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper mapper;
    private final CommonUtils commonUtils;

    @Autowired
    public UserService(UserMapper mapper, CommonUtils commonUtils){
        this.mapper = mapper;
        this.commonUtils = commonUtils;
    }

    public int insUser(UserInsDto dto){
        // 무조건 대문자로 들어갈수있게 하기
        char gender = Character.toUpperCase(dto.getGender());
        if(!(gender == 'F' || gender == 'M')){
            return -1;
        }
        dto.setGender(gender);              // M아니면 F만들어올수있게 하는 구문

        // 비밀번호 해시코드화
        String hash = commonUtils.encodeSha256(dto.getPw());
        dto.setPw(hash);
        try {
            return mapper.insUser(dto);
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }

    }

    public int login(UserLoginDto dto){
        UserLoginVo vo = mapper.loginUser(dto);
        if (vo == null){
            return 2;
        }
        String hash = commonUtils.encodeSha256(dto.getPw());
        if (vo.getPw().equals(hash)){
            return 1;
        }
        return 3;
    }

    public int updUserPw(UserUpdPw pw){
        String hash = commonUtils.encodeSha256(pw.getPw());
        pw.setPw(hash);
        return mapper.updUserPw(pw);
    }
}
