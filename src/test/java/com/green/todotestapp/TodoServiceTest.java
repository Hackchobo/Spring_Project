package com.green.todotestapp;

import com.green.todotestapp.model.TodoInsDto;
import com.green.todotestapp.model.TodoInsParam;
import com.green.todotestapp.model.TodoRes;
import com.green.todotestapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import( {TodoServiceImpl.class})
@TestPropertySource(properties = {
        "file.dir=/home/download"
}) // 이부분을 넣어주면 TodoServicelmpl 쪽 file_DIR에 home/download 부분이 들어 감
class TodoServiceTest {

    @MockBean
    private TodoMapper mapper;

    @Autowired
    private TodoService service;

    @Test
    void insTodo() throws Exception {

        String originalFileNm = "95d52bb0-c1a5-4409-8463-c25517e7f250.jpg";
        String contentType = "jpg";
        String filePath = "D:/home/download/user/1/"+ originalFileNm;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        MultipartFile pic = new MockMultipartFile("pic", originalFileNm, contentType, fileInputStream);

        TodoInsParam p1= new TodoInsParam();
        p1.setCtnt("테스트3");
        p1.setPic(pic);

        when(mapper.insTodo(any())).thenReturn(1);

        TodoRes r1 = service.insTodo(p1);

        assertEquals(p1.getCtnt(), r1.getCtnt());

        verify(mapper).insTodo(any());
        /*//when
        final Long VAL = 3L;
        when(mapper.insTodo(any())).thenReturn(3); // 역활을 줌

        TodoInsDto p1 = new TodoInsDto();
        p1.setCtnt("테스트1");
        p1.setPic("main.jpg");
        p1.setItodo(VAL);
        Long r1 = service.insTodo(p1);
        assertEquals(VAL,r1);

        verify(mapper).insTodo(any());*/
    }

    /*@Test
    void insTodo2() {
        final Long VAL = 4L;
        //when
        when(mapper.insTodo(any())).thenReturn(4);
        TodoInsDto p1 = new TodoInsDto();
        p1.setItodo(VAL);
        Long r1 = service.insTodo(p1);
        assertEquals(VAL,r1);

        verify(mapper).insTodo(any());
    }*/

    @Test
    @DisplayName("Todo 리스트")
    void selTodo() {
        TodoVo vo1 = new TodoVo();
        vo1.setItodo(1L);
        vo1.setCtnt("내용1");
        vo1.setFinishedAt(1);

        TodoVo vo2 = new TodoVo();
        vo2.setItodo(2L);
        vo2.setCtnt("내용2");
        vo2.setPic("main.jpg");
        vo2.setFinishYn(0);

        List<TodoVo> rList = new ArrayList<>();
        rList.add(vo1);
        rList.add(vo2);

        when(mapper.selTodo()).thenReturn(rList);

        List<TodoVo> serviceList = service.selTodo();

        assertEquals(rList.size(), serviceList.size());
        assertEquals(rList.get(0).getItodo(), serviceList.get(0).getItodo());
        assertEquals(rList.get(0).getCtnt(), serviceList.get(0).getCtnt());
        assertEquals(rList.get(0).getFinishYn(), serviceList.get(0).getFinishYn());

        assertEquals(rList.get(1).getItodo(), serviceList.get(1).getItodo());
        assertEquals(rList.get(1).getCtnt(), serviceList.get(1).getCtnt());
        assertEquals(rList.get(1).getPic(), serviceList.get(1).getPic());
        assertEquals(rList.get(1).getFinishYn(), serviceList.get(1).getFinishYn());
    }

}