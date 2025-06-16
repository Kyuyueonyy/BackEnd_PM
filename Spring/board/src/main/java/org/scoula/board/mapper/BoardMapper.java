package org.scoula.board.mapper;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.scoula.board.domain.BoardVO;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

//@Repository //저장(DB)
//@Component + DB 예외처리 기능을 합친 것
//@Mapper
public interface BoardMapper {
    //@Select("select * from tbl_board order by no desc")
    public List<BoardVO> getList();

    //상세 검색
    public BoardVO get(Long no);

    //게시판 글쓰기 -> vo가 거의 대부분임
    public void create(BoardVO board);

    //게시판 글 업데이트
    public int update(BoardVO boardVO);

    //게시판 글 삭제
    public int delete(long no); //pk값만 알고있으면 지울 수 있음

}