package com.green.zero.board;

import com.green.zero.board.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BoardService {

    private BoardMapper mapper;

    @Autowired
    public BoardService(BoardMapper mapper) {
        this.mapper = mapper;
    }

    public int insBoard(BoardInsDto dto){
        BoardEntity entity = new BoardEntity();
        entity.setTitle(dto.getTitle());
        entity.setCtnt(dto.getCtnt());
        entity.setIuser(dto.getIuser());
        int result = mapper.insBoard(entity);
        if (result == 1) {
            return entity.getIboard();
        }
        return result;
    }

    public List<BoardSelVo> selBoardAll(BoardSelDto dto){
        int startIdx = (dto.getPage()-1) * dto.getRow();
        dto.setStartIdx(startIdx);
        return mapper.selBoardAll(dto);
    }

    public int selBoardMax(int row){
        int count = mapper.selBoardMax(row);
        return (int)Math.ceil(count/row);
    }

    public BoardSelVo selBoardDetail(BoardSelDto dto){
        return mapper.selBoardDetail(dto);
    }

    public int delBoard(BoardDelDto dto){
        return mapper.delBoard(dto);
    }

    public int updBoard(BoardUpdDto dto){
        return mapper.updBoard(dto);
    }
}
