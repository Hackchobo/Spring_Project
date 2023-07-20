package com.green.todotestapp.model;

import lombok.Data;

@Data
public class TodoInsDto { // 받을때
    private Long itodo;
    private String ctnt;
    private String pic;

    /*public TodoInsDto(TodoInsParam p){
        this.ctnt = p.getCtnt();
        this.pic = p.getPic();
    }*/
}
