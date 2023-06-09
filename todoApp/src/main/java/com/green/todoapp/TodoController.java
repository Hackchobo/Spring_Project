package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoFinishDto;
import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todo")
public class TodoController {

    private TodoSevice sevice;

    @Autowired
    public TodoController(TodoSevice sevice) {
        this.sevice = sevice;
    }

    @PostMapping
    public int postTodo(@RequestBody TodoInsDto dto){
        return sevice.insTodo(dto);
    }

    @GetMapping
    public List<TodoVo> getTodo(/*@RequestParam Long page,
                                   @RequestParam Long row,
                                   @RequestParam Long delYn*/){
        /*TodoSelAllDto dto = new TodoSelAllDto();
        dto.setPage(page);
        dto.setRow(row);
        dto.setDelYn(delYn);*/
        return sevice.selTodo();
    }

    @PatchMapping("/{itodo}")
    public int patchTodo(@RequestBody TodoFinishDto dto){
        return sevice.updFinish(dto);
    }

    /*@GetMapping
    public String getTodo() {
        return "test";
    }*/

    @DeleteMapping("/{itodo}")
    public int deleteTodo(@RequestParam int itodo){
        return sevice.updDelete(itodo);
    }
}
