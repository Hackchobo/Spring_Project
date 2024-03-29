package com.green.todotestapp;

import com.green.todotestapp.model.TodoInsDto;
import com.green.todotestapp.model.TodoInsParam;
import com.green.todotestapp.model.TodoRes;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/todo")
public class TodoController {

    private final TodoService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TodoRes postTodo(@RequestPart String ctnt,@RequestPart MultipartFile pic){
//        TodoInsDto dto = new TodoInsDto(p);
        TodoInsParam p = new TodoInsParam();
        p.setCtnt(ctnt);
        p.setPic(pic);
        return service.insTodo(p);
    }
}
