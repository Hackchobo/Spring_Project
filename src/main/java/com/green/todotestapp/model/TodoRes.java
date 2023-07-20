package com.green.todotestapp.model;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class TodoRes { // 응답할때 사용되는 것
    private Long itodo;
    private String ctnt;
    private String pic;
    private LocalDateTime createdAt;
    private int finishYn;

    public TodoRes(TodoInsDto dto){
        this.itodo = dto.getItodo();
        this.ctnt = dto.getCtnt();
        this.pic = dto.getPic();
        this.createdAt = LocalDateTime.now();
        this.finishYn = 0;
    }
}
