package com.green.babyfood1.User.model;

import lombok.Data;

@Data
public class AdminInsDto {
    private String email;
    private String password;
    private String name;
    private String mobileNb;
    private String address;
    private String nickNm;
}
