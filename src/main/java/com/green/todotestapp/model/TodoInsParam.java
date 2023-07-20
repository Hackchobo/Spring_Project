package com.green.todotestapp.model;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class TodoInsParam { // 요청할때
    private String ctnt;
    private MultipartFile pic;

}
