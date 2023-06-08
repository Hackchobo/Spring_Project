package com.green.zero.user.model;

import lombok.Data;

@Data
public class UserInsDto {
    private String uid;
    private String pw;
    private String nm;
    private char gender;
    private String addr;
}
