package org.scoula.service;

import org.scoula.domain.BoardVO;

import java.util.List;

public interface BoardService {
    List<BoardVO> getAllBoardByMapper();
    List<BoardVO> getAllBoardByAnnotation();

}
