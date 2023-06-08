package com.green.toto2.cmt;

import com.green.toto2.cmt.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CmtMapper {
    int insCmt(CmtInsDto dto);
    List<CmtEntity> selCmtAll(CmtSelAll all);
    int updCmt(CmtUpdDto dto);
    int delCmt(CmtDelDto dto);

}
