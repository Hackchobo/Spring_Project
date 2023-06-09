package com.green.zero.board;

import com.green.zero.board.model.*;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board")
public class BoardController {

    private BoardService service;

    @Autowired
    public BoardController(BoardService service) {
        this.service = service;
    }
    // 글등록
    @PostMapping
    public int PostBoard(@RequestBody BoardInsDto dto){
        return service.insBoard(dto);
    }
    // 페이징 처리된 게시물 리스트
    @GetMapping
    public List<BoardSelVo> getBoardAll(@RequestParam(defaultValue = "1") @Min(value = 1) int page,
                                     @RequestParam(defaultValue = "30") int row){
        BoardSelDto dto = new BoardSelDto();
        dto.setPage(page);
        dto.setRow(row);
        return service.selBoardAll(dto);
    }
    // 게시물 최대 페이지
    @GetMapping("/maxpage")
    public int getBoardMaxPage(@RequestParam int row){
        return service.selBoardMax(row);
    }
    // 게시물을 유저 아이디 별로 보기
    @GetMapping("/{iboard}")
    public BoardSelVo getBoardDetail(@PathVariable int iboard){
        BoardSelDto dto = new BoardSelDto();
        dto.setIboard(iboard);
        return service.selBoardDetail(dto);
    }

    @DeleteMapping("/{iboard}")
    public int deleteBoard(@RequestParam int iboard,
                           @RequestParam int iuser){
        BoardDelDto dto = new BoardDelDto();
        dto.setIboard(iboard);
        dto.setIuser(iuser);
        return service.delBoard(dto);
    }

    @PutMapping
    public int putBoard(@RequestBody BoardUpdDto dto){
        return service.updBoard(dto);
    }
}
