package com.green.todoapp.model;

import lombok.Data;

@Data
public class TodoSelAllDto {
    private int startIdx;
    private int page;
    private int row;
    private int delYn;
}
