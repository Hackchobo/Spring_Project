package com.green.babyfood1.User.model;

import lombok.Data;

@Data
public class UserInsDto {
    private String email;
    private String password;
    private String name;
    private String birthday;
    private String mobileNb;
    private String address;
    private String nickNm;
}
