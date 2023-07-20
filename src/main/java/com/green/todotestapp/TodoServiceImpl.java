package com.green.todotestapp;

import com.green.todotestapp.model.TodoInsDto;
import com.green.todotestapp.model.TodoInsParam;
import com.green.todotestapp.model.TodoRes;
import com.green.todotestapp.model.TodoVo;
import com.green.todotestapp.utils.MyFileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
public class TodoServiceImpl implements TodoService{

    private final TodoMapper MAPPER;
    private final String FILE_DIR;

    public TodoServiceImpl(TodoMapper MAPPER, @Value("${file.dir}") String FileDir) {
        this.MAPPER = MAPPER;
        this.FILE_DIR = MyFileUtils.getAbsolutePath(FileDir);
    }

    @Override
    public TodoRes insTodo(TodoInsParam p) {
       // File tempDic = new File(FILE_DIR, "/temp");
        File tempDic = new File(FILE_DIR + "/temp"); // 위와 동일한 결과
        if(!tempDic.exists()){
            tempDic.mkdirs();
        }

        //저장용 파일명
        String savedFileNm = MyFileUtils.makeRandomFileNm(p.getPic().getOriginalFilename());

        File tempFile = new File(tempDic.getPath(),savedFileNm);
        try {
            p.getPic().transferTo(tempFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        TodoInsDto dto = new TodoInsDto();
        dto.setCtnt(p.getCtnt());
        dto.setPic(savedFileNm);
        int result = MAPPER.insTodo(dto); // 넘어오는 값은 영향받은행이 넘어온다.
        if (result == 1){
            //파일이동
            String targetDicPath = FILE_DIR + "/todo/" + dto.getItodo();//
            File targetDic = new File(targetDicPath);
            if(!targetDic.exists()){
                targetDic.mkdirs();
            }
            File targetFile = new File(targetDicPath+"/"+ savedFileNm); //
            tempFile.renameTo(targetFile);// 이동 부분임
            //TodoRes res = new TodoRes(dto);
            /*TodoRes res = new TodoRes();
            res.setItodo(dto.getItodo());
            res.setCtnt(dto.getCtnt());
            res.setPic(dto.getPic());
            res.setCreatedAt(LocalDateTime.now());
            res.setFinishYn(0);*/
            return new TodoRes(dto);
        }
        return null;
    }

    @Override
    public List<TodoVo> selTodo() {
        return MAPPER.selTodo();
    }
}