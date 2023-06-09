package com.green.zero.board;

import com.green.zero.board.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insBoard(BoardEntity dto);
    List<BoardSelVo> selBoardAll(BoardSelDto dto);
    int selBoardMax(int row);
    BoardSelVo selBoardDetail(BoardSelDto dto);
    int delBoard(BoardDelDto dto);
    int updBoard(BoardUpdDto dto);
}
