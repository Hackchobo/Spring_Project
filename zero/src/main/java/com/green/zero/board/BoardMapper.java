package com.green.zero.board;

import com.green.zero.board.model.BoardInsDto;
import com.green.zero.board.model.BoardSelDto;
import com.green.zero.board.model.BoardSelVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardInsDto dto);
    List<BoardSelVo> selBoardAll(BoardSelDto dto);
    int selBoardMax(int row);
}
