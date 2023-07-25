package com.green.todotestapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.todotestapp.model.TodoRes;
import com.green.todotestapp.utils.MyFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class TodoIntegrationTest extends IntegrationTest{

    @Autowired
    private TestRestTemplate restTemplate; // 지워도 상관 없다

    @Value("${file.dir}")
    private String fileDir;

    @Test
    @Rollback(value = false)
    public void postTodo() throws Exception{
        String originalFileNm = "95d52bb0-c1a5-4409-8463-c25517e7f250.jpg";
        String contentType = "jpg";
        String filePath = "D:/home/download/user/1/"+ originalFileNm;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        MockMultipartFile pic = new MockMultipartFile("pic", originalFileNm, contentType, fileInputStream);


        MvcResult mr = mvc.perform(multipart("/api/todo")
                        .file(pic)
                        .part(new MockPart("ctnt", "테스트3".getBytes(StandardCharsets.UTF_8)))
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mr.getResponse().getContentAsString();
        TodoRes todoRes = om.readValue(content, TodoRes.class);
        log.info("todoRes : {}", todoRes);

        String dicPath = MyFileUtils.getAbsolutePath(fileDir + "/todo/" + todoRes.getItodo()); // 윈도우와 리눅스에서 사용 할수있게 하는 구문
        File dicfile = new File(dicPath);
        assertEquals(true, dicfile.exists(), "1번 폴더가 없음");

        File picFile = new File(dicfile, todoRes.getPic());
        assertEquals(true, picFile.exists(), "1번 이미지가 없음");
        assertEquals("테스트3",todoRes.getCtnt());

        // -----------------------------------------------------------------------------------

        mvc.perform(multipart("/api/todo")
                        .file(pic)
                        .part(new MockPart("ctnt", "테스트4".getBytes(StandardCharsets.UTF_8)))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }
}
