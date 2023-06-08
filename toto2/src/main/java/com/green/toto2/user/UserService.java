package com.green.toto2.user;

import com.green.toto2.user.model.UserInsDto;
import com.green.toto2.user.model.UserLoginDto;
import com.green.toto2.user.model.UserLoginVo;
import com.green.toto2.user.model.UserPatchPwDto;
import com.green.toto2.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserMapper mapper;
    private final CommonUtils commonUtils;

    @Autowired
    public UserService(UserMapper mapper, CommonUtils commonUtils) {
        this.mapper = mapper;
        this.commonUtils = commonUtils;
    }
    // 회원가입
    public int insUser(UserInsDto dto){
        //성별 항상 대문자 변경
        char gender = Character.toUpperCase(dto.getGender());
        dto.setGender(gender);

        // 비밀 번호 암호화 util의 CommonUtils를 이용
        String hashPw = commonUtils.encodeSha256(dto.getUpw());
        dto.setUpw(hashPw);
        try {
            return mapper.insUser(dto);
        } catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    // 로그인
    public int login(UserLoginDto dto){
        UserLoginVo vo = mapper.loginUser(dto);
        if(vo == null){
            return 2;
        }
        String hashedPw = commonUtils.encodeSha256(dto.getUpw());
        if(vo.getUpw().equals(hashedPw)){
            return 1;
        }
        return 3;
    }
    // 비밀번호 바꾸기
    public int updUserPw(UserPatchPwDto dto){
        String hashedPw = commonUtils.encodeSha256(dto.getUpw());
        dto.setUpw(hashedPw);
        return mapper.updUserPw(dto);
    }
}
