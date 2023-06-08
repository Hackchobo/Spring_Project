package com.green.toto2.board;

import com.green.toto2.board.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    int insboard(BoardInsDto dto);
    List<BoardSelAll> selboardAll(BoardPage page);
    BoardDetail selboardDetail(BoardPage page);
    int updBoard(BoardUpd upd);
    int delBoard(BoardDel del);
}
