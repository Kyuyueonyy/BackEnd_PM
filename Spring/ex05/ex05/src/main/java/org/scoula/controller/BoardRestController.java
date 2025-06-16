package org.scoula.controller;

import org.scoula.domain.BoardVO;
import org.scoula.service.BoardService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BoardRestController {

    private BoardService boardService;

    //생성자 주입
    public BoardRestController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping("/api/mapper/boards")
    public List<BoardVO> getBoardList() {
        return boardService.getAllBoardByMapper();
    }

    @GetMapping("/api/annotation/boards")
    public List<BoardVO> getBoardList2() {
        return boardService.getAllBoardByAnnotation();
    }

}
