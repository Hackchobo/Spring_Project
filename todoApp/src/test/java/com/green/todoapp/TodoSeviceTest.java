package com.green.todoapp;

import com.green.todoapp.model.TodoEntity;
import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoSelAllDto;
import com.green.todoapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({TodoSevice.class})
class TodoSeviceTest {

    @MockBean
    private TodoMapper mapper;

    @Autowired // 빨간줄이 뜨더라도 오류가 아님을 명시 해야 한다.
    private TodoSevice service;

    @Test
    @DisplayName("TodoSevice - Todo등록")
    void insTodo() {
        TodoEntity entity = new TodoEntity();
        entity.setCtnt("내용 입력");
        when(mapper.insTodo(entity)).thenReturn(1);

        TodoInsDto dto = new TodoInsDto();
        dto.setCtnt("내용 입력");
        int result = service.insTodo(dto);

        assertEquals(0,result);

        verify(mapper).insTodo(any(TodoEntity.class));
    }

    @Test
    @DisplayName("TodoService - Todo 리스트 가져오기")
    void selTodo() {
        List<TodoVo> mockList = new ArrayList<>();
        mockList.add(new TodoVo(1,"테스트","2023",null,1,"2023-05-11"));
        mockList.add(new TodoVo(2,"테스트2","2022","abc.jpg",0,null));
        when(mapper.selTodo()).thenReturn(mockList);

        List<TodoVo> todoVos = service.selTodo();

        assertEquals(2,todoVos.size()); //검증
        assertEquals("테스트",todoVos.get(0).getCtnt()); //검증
        assertEquals(mockList, todoVos);

        verify(mapper).selTodo();


    }
}