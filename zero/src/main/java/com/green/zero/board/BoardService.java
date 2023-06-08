package com.green.zero.board;

import com.green.zero.board.model.BoardInsDto;
import com.green.zero.board.model.BoardSelDto;
import com.green.zero.board.model.BoardSelVo;
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
        return mapper.insBoard(dto);
    }

    public List<BoardSelVo> selBoardAll(BoardSelDto dto){
        int startIdx = (dto.getPage()-1) * dto.getRow();
        dto.setStartIdx(startIdx);
        return mapper.selBoardAll(dto);
    }

    public int selBoardMax(int row){
        return mapper.selBoardMax(row);
    }
}
