package com.green.todoapp;

import com.google.gson.Gson;
import com.green.todoapp.model.TodoFinishDto;
import com.green.todoapp.model.TodoInsDto;
import com.green.todoapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.hasSize;

@WebMvcTest(TodoController.class) // 컨트롤단에서 테스트를 할때에는 기본적으로 사용 되는 것 (1)
class TodoControllerTest {

    @Autowired // 컨트롤단에서 테스트를 할때에는 기본적으로 사용 되는 것 (2)
    private MockMvc mvc;

    @MockBean // 가짜 서비스단을 만드는 것임(얕은 검사단에서 활용), 빈등록을 하지만 가짜로 서비스 등록을 하는것이다.
    private TodoSevice sevice;

    @Test // JUNIT 언어임
    @DisplayName("TODO - Todo 등록")
    void postTodo() throws Exception {
        //Given - 테스트셋팅(이유 : 컨트롤은 서비스를 참조하는데 다만들면 일이 커짐 그래서 호출이 된다면 가짜 서비스단을 이용하여 하는것
        //테스트에서 구체화하고자 하는 행동을 시작하기 전에 테스트 상태를 설명하는 부분
        given(sevice.insTodo(any(TodoInsDto.class))).willReturn(3);
        //When - 실제 실행
        //구체화하고자 하는 그 행동 - 데이터를 가져오는 단계
        TodoInsDto dto = new TodoInsDto();
        dto.setCtnt("빨래 개기");

        Gson gson = new Gson();
        //String json = "{\"ctnt\": \"빨래 개기\"}";
        String json = gson.toJson(dto); // 48번줄의 역활을 해준다.
        ResultActions ra =mvc.perform(post("/api/todo")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON));


        //Then - 검증
        //어떤 특정한 행동 때문에 발생할거라고 예상되는 변화에 대한 설명
        ra.andExpect(status().isOk()) //
        .andExpect(content().string("3")) //
        .andDo(print()); // 콘솔창에 프린트를 명령하는 명령문
        verify(sevice).insTodo(any()); //verify 실행됬는지 확인해주는 역활을 한다.


    }

    @Test // JUNIT 언어임
    @DisplayName("TODO - Todo 리스트")
    void getTodo() throws Exception {
        //given - when - then

        List<TodoVo> mockList = new ArrayList<>();
        mockList.add(new TodoVo(1,"테스트","2023",null,1,"2023-05-11"));
        mockList.add(new TodoVo(2,"테스트2","2022","abc.jpg",0,null));
        given(sevice.selTodo()).willReturn(mockList); // wullReturn뒤에는 리턴하는 객체 주소값을 넣어 줘야 한다.

        ResultActions ra =mvc.perform(get("/api/todo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(mockList.size())))
                .andExpect(jsonPath("*.itodo").exists()) // *(와일드) itodo 가 값에 들어가 있는지 확인 해준다.
                .andExpect(jsonPath("[0].itodo").value(1)) //1의 값이 들어있는지 확인 해주는 구문
                .andDo(print());

    }

    @Test // JUNIT 언어임
    @DisplayName("TODO - Todo 패치")
    void updTodo() throws Exception{

        //given
        given(sevice.updFinish(any(TodoFinishDto.class))).willReturn(1);

        // when
        TodoFinishDto dto = new TodoFinishDto();
        dto.setItodo(1);

        Gson gson = new Gson();

        String json = gson.toJson(dto);

        mvc.perform(patch("/api/todo")
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON))

        //Then
                .andExpect(status().isOk())
                .andExpect(content().string("1"))
                .andDo(print());
        verify(sevice).updFinish(any());
    }

    @Test
    @DisplayName("TODO - Todo 삭제")
    void delTodo() throws Exception{
        int itodo = 10;

        given(sevice.updDelete(anyInt())).willReturn(itodo);

        ResultActions ra = mvc.perform(delete("/api/todo/?itodo=" + itodo)
                            .param("itodo", String.valueOf(itodo)));

        ra.andExpect(status().isOk())
                .andExpect(content().string(String.valueOf(itodo)))
                .andDo(print());

        verify(sevice).updDelete(anyInt());


    }
}