package org.scoula.board.service;
import org.apache.ibatis.annotations.Mapper;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.dto.BoardDTO;

import java.util.List;
import java.util.Optional;

public interface BoardService {
    /*게시글 전체 조회*/
    public List<BoardDTO> getList();

    /*게시글 단건 조회*/
    public BoardDTO get(Long no);

    /*게시글 생성*/
    public void create(BoardDTO board);

    /*게시글 수정*/
    public boolean update(BoardDTO board);

    /*게시글 삭제*/
    public boolean delete(Long no);
}