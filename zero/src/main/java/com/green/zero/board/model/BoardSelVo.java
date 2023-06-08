package com.green.zero.board.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BoardSelVo {
    private int iboard;
    private String title;
    private String ctnt;
    private int iuser;
    private String createdAt;
    private String updatedAt;
}
