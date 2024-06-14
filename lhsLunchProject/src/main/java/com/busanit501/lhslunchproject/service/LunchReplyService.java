package com.busanit501.lhslunchproject.service;


import com.busanit501.lhslunchproject.dto.LunchReplyDTO;
import com.busanit501.lhslunchproject.dto.PageRequestDTO;
import com.busanit501.lhslunchproject.dto.PageResponseDTO;

public interface LunchReplyService {
    // 댓글 , crud
    Long register(LunchReplyDTO lunchReplyDTO);
    LunchReplyDTO read(Long rno);
    void update(LunchReplyDTO lunchReplyDTO);
    void delete(Long rno);
    // 댓글 페이징 처리해서 목록 출력.
    PageResponseDTO<LunchReplyDTO> getListOfBoard(Long bno, PageRequestDTO pageRequestDTO);
}
