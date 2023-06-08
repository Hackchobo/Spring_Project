package com.green.toto2.cmt;

import com.green.toto2.cmt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmtService {

    private CmtMapper mapper;

    @Autowired
    public CmtService(CmtMapper mapper){
        this.mapper = mapper;
    }

    public int insCmt(CmtInsDto dto){
        return mapper.insCmt(dto);
    }

    public List<CmtEntity> selCmtAll(CmtSelAll all){
        int startIdx = (all.getPage()-1) * all.getRow();
        all.setStartIdx(startIdx);
        return mapper.selCmtAll(all);
    }

    public int updCmt(CmtUpdDto dto){
        return mapper.updCmt(dto);
    }

    public int delCmt(CmtDelDto dto){
        return mapper.delCmt(dto);
    }
}
