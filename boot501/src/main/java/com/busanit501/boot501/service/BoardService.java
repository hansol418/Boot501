package com.busanit501.boot501.service;

import com.busanit501.boot501.dto.BoardDTO;

public interface BoardService {
  Long register(BoardDTO boardDTO);
  //하나 조회, read
  BoardDTO read(Long bno);
  void update(BoardDTO boardDTO);
  void delete(Long bno);
}







