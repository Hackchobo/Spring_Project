package com.green.zero.cmt.model;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class CmtRes {
    private int maxPage;
    private int isMore;
    private List<CmtSelVo> list;

}
