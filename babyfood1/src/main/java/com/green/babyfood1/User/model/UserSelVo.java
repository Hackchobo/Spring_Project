package com.green.babyfood1.User.model;

import lombok.Data;

@Data
public class UserSelVo {
    private String email;
    private String password;
    private String image;
    private String name;
    private String birthday;
    private String mobileNb;
    private String createdAt;
    private int admin;
    private String address;
    private String nickNm;
    private int point;
}
