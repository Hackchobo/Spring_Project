package com.green.toto2.cmt;

import com.green.toto2.cmt.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/toto/cmt")
public class CmtController {

    private CmtService service;

    @Autowired
    public CmtController(CmtService service) {
        this.service = service;
    }

    @PostMapping
    public int PostCmt(@RequestBody CmtInsDto dto){
        return service.insCmt(dto);
    }

    @GetMapping
    public List<CmtEntity> GetCmtAll(@RequestParam (defaultValue = "1") int page,
                                     @RequestParam (defaultValue = "30") int row){
        CmtSelAll all = new CmtSelAll();
        all.setPage(page);
        all.setRow(row);
        return service.selCmtAll(all);
    }

    @PutMapping
    public int PutCmt(@RequestBody CmtUpdDto dto){
        return service.updCmt(dto);
    }

    @DeleteMapping("/cmt/{itotoCmt}")
    public int Delete(@RequestParam int itoto_cmt,
                      @RequestParam int itoto){
        CmtDelDto dto = new CmtDelDto();
        dto.setItotoCmt(itoto_cmt);
        dto.setItoto(itoto);
        return service.delCmt(dto);

    }
}
