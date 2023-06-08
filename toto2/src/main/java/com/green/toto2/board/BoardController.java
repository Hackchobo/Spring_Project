package com.green.toto2.board;

import com.green.toto2.board.model.*;
import jakarta.validation.constraints.Min;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toto")
public class BoardController {

    private BoardService service;

    @Autowired
    public BoardController(BoardService service){
        this.service = service;
    }

    @PostMapping
    public int PostBoard(@RequestBody BoardInsDto dto){
        return service.insboard(dto);
    }

    @GetMapping
    public List<BoardSelAll> GetBoardAll(@RequestParam (defaultValue = "1") @Min(value = 1) int page ,
                                         @RequestParam(defaultValue = "30") int row){
        BoardPage page1 = new BoardPage();
        page1.setPage(page);
        page1.setRow(row);
        return service.selboardAll(page1);
    }

    @GetMapping("/{itoto}")
    public BoardDetail GetBoardDetail(@PathVariable int itoto){
        BoardPage page = new BoardPage();
        page.setItoto(itoto);
        return service.selboardDetail(page);
    }

    @PutMapping
    public int PutBoard(@RequestBody BoardUpd upd) {
        return service.updBoard(upd);
    }

    @DeleteMapping("/{itoto}")
    public int DelBoard(@PathVariable int itoto){
        BoardDel del = new BoardDel();
        del.setItoto(itoto);
        return service.delBoard(del);
    }
}
