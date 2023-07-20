package com.green.todotestapp;

import com.green.todotestapp.model.TodoInsDto;
import com.green.todotestapp.model.TodoUpdDto;
import com.green.todotestapp.model.TodoVo;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@MybatisTest
@ActiveProfiles("test") // yaml에 test라고 써서 test임
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoMapperTest {

    @Autowired
    private TodoMapper mapper;

    @Test
    void insTodo() {

        TodoInsDto p1 = new TodoInsDto();
        p1.setCtnt("테스트1");
        p1.setPic("main.jpg");

        int r1 = mapper.insTodo(p1);

        assertEquals(1,r1);
        assertEquals(3,p1.getItodo());

        TodoInsDto p2 = new TodoInsDto();
        p2.setCtnt("테스트2");

        int r2 = mapper.insTodo(p2);

        assertEquals(1,r2);
        assertEquals(4,p2.getItodo());

        List<TodoVo> list = mapper.selTodo();
        assertEquals(4, list.size());

        TodoVo item3 = list.get(2);
        assertEquals(p1.getCtnt(), item3.getCtnt());
        assertEquals(p1.getPic(), item3.getPic());

        TodoVo item4 = list.get(3);
        assertEquals(p2.getCtnt(), item4.getCtnt());
        assertEquals(p2.getPic(), item4.getPic());
    }

    @Test
    public void selTodo() {
        List<TodoVo> list = mapper.selTodo();
        assertEquals(2,list.size());

        TodoVo item1 = list.get(0);
        assertEquals(1,item1.getItodo());

        assertNotNull(item1.getCtnt());
        assertEquals("뻐큥머겅",item1.getCtnt());

        TodoVo item2 = list.get(1);
        assertEquals(2,item2.getItodo());

        assertNotNull(item2.getCtnt());
        assertEquals("마이아파",item2.getCtnt());
    }

    @Test
    public void updTodo() {

        TodoUpdDto u1 = new TodoUpdDto();
        u1.setItodo(1L);
        u1.setCtnt("커리커리");
        u1.setPic("Sub.jpg");
        int r1 = mapper.updTodo(u1);
        assertEquals(1,r1);

        List<TodoVo> list1 = mapper.selTodo();

        assertEquals(1,list1.get(0).getItodo()); // get만사용할시 전체 변경됬는지 해시코드뜬다.
        assertEquals("커리커리",list1.get(0).getCtnt());
        assertEquals("Sub.jpg",list1.get(0).getPic());

        TodoUpdDto u2 = new TodoUpdDto();
        u2.setItodo(2L);
        u2.setCtnt("치킨치킨");
        u2.setPic("ChiChi.jpg");
        int r2 = mapper.updTodo(u2);
//        assertEquals(1,r2);

        List<TodoVo> list2 = mapper.selTodo();

        assertEquals(2,list2.get(1).getItodo());
        assertEquals("치킨치킨",list2.get(1).getCtnt());
        assertEquals("ChiChi.jpg",list2.get(1).getPic());
    }
}