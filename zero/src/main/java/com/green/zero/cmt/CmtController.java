package com.green.zero.cmt;

import com.green.zero.cmt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/board/cmt")
public class CmtController {

    private final CmtService service;

    @Autowired
    public CmtController(CmtService service) {
        this.service = service;
    }

    @PostMapping
    public int postCmt(@RequestBody CmtInsDto dto){
        return service.insCmt(dto);
    }

    @GetMapping("/{iboard}")
    public CmtRes getCmt(@PathVariable int iboard,
                                 @RequestParam(defaultValue = "1") int page,
                                 @RequestParam (defaultValue = "20") int row){
        CmtSelDto dto= new CmtSelDto();
        dto.setIboard(iboard);
        dto.setPage(page);
        dto.setRow(row);
        return service.selCmt(dto);
    }

    @PutMapping("/{iboardCmt}")
    public int putCmt(@PathVariable int iboardCmt,
                      @RequestParam int iuser,
                      @RequestParam String ctnt){
        CmtUpdDto dto = new CmtUpdDto();
        dto.setIboardCmt(iboardCmt);
        dto.setIuser(iuser);
        dto.setCtnt(ctnt);
        return service.updCmt(dto);
    }

    @DeleteMapping("/{iboardCmt}")
    public int deleteCmt(@PathVariable int iboardCmt,
                         @RequestParam int iuser){
        CmtDelDto dto = new CmtDelDto();
        dto.setIboardCmt(iboardCmt);
        dto.setIuser(iuser);
        return service.delCmt(dto);
    }
}
