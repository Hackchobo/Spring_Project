package com.green.toto2.board;

import com.green.toto2.board.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private BoardMapper mapper;

    @Autowired
    public BoardService(BoardMapper mapper){
        this.mapper = mapper;
    }

    public int insboard(BoardInsDto dto){
        return mapper.insboard(dto);
    }

    public List<BoardSelAll> selboardAll(BoardPage page){
        int startIdx = (page.getPage()-1) * page.getRow();
        page.setStartIdx(startIdx);
        return mapper.selboardAll(page);
    }

    public BoardDetail selboardDetail(BoardPage page){
        return mapper.selboardDetail(page);
    }

    public int updBoard(BoardUpd upd){
        return mapper.updBoard(upd);
    }

    public int delBoard(BoardDel del){
        return mapper.delBoard(del);
    }
}
