package com.green.zero.cmt;

import com.green.zero.cmt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CmtService {

    private final CmtMapper mapper;

    @Autowired
    public CmtService(CmtMapper mapper) {
        this.mapper = mapper;
    }

    public int insCmt(CmtInsDto dto){
        CmtInsVo vo = new CmtInsVo();
        vo.setIuser(dto.getIuser());
        vo.setIboard(dto.getIboard());
        vo.setCtnt(dto.getCtnt());
        int result = mapper.insCmt(vo);
        if (result ==1 ){
            return vo.getIboardCmt();
        }
        return result;
    }

    public CmtRes selCmt(CmtSelDto dto){
        int startIdx = (dto.getPage()-1) * dto.getRow();
        dto.setStartIdx(startIdx);
        List<CmtSelVo> list = mapper.selCmt(dto);

        int rowCnt = mapper.selBoardCmtRowCountByIBoard(dto.getIboard());
        int maxPage = (int)Math.ceil((double)rowCnt / dto.getRow());
        int isMore = maxPage > dto.getPage() ? 1 : 0;

        return CmtRes.builder()
                    .list(list)
                    .isMore(isMore)
                    .maxPage(maxPage)
                    .build();
    }

    public int updCmt(CmtUpdDto dto){
        return mapper.updCmt(dto);
    }

    public int delCmt(CmtDelDto dto){
        return mapper.delCmt(dto);
    }
}
