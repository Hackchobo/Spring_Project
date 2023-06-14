package com.green.todoapp;

import com.green.todoapp.model.TodoFinishDto;
import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoSevice {

    private final TodoMapper mapper;

    @Autowired
    public TodoSevice(TodoMapper mapper) {
        this.mapper = mapper;
    }

    public int insTodo(TodoInsDto dto){
        TodoEntity entity = new TodoEntity();
        entity.setCtnt(dto.getCtnt());
        int result = mapper.insTodo(entity);
        if(result == 1){
            return entity.getItodo();
        }
        return result;
    }

    public List<TodoVo>selTodo(){
        /*Long startIdx =((dto.getPage()-1) * dto.getRow());
        dto.setStartIdx(startIdx);*/
        return mapper.selTodo();
    }

    public int updFinish(TodoFinishDto dto){
        TodoEntity entity = new TodoEntity();
        entity.setItodo(dto.getItodo());

        int result = mapper.updFinish(entity);
        System.out.println(entity.getFinishYn());
        return entity.getFinishYn();
    }

    public int updDelete(int itodo){
        TodoEntity entity = new TodoEntity();
        entity.setItodo(itodo);
        return mapper.updDelete(entity);
    }
}
