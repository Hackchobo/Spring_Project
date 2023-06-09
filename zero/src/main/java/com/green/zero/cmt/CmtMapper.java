package com.green.zero.cmt;

import com.green.zero.cmt.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmtMapper {
    int insCmt(CmtInsVo vo);
    List<CmtSelVo> selCmt(CmtSelDto dto);
    int selBoardCmtRowCountByIBoard(int iboard);
    int updCmt(CmtUpdDto dto);
    int delCmt(CmtDelDto dto);
}
