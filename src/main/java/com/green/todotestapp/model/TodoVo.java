package com.green.todotestapp.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class TodoVo {
    private Long itodo;
    private String ctnt;
    private LocalDate createdAt;
    private int delYn;
    private String pic;
    private int finishYn;
    private int finishedAt;
}
