package org.scoula.board.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/board")
@RequiredArgsConstructor
@Log4j2
//@Controller + @ResponseBody
@RestController //views로 넘어가지 않고 모두 data로 응답하겠다!
@Api(tags = "게시글 관리")
public class BoardController {

private final BoardService service;//생성자 주입

    @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 얻는 API")
    @GetMapping("") // ==> /api/board
    //@ResponseBody//컨트롤러에서 views로 넘어가지 않고
    //http의 body에 리턴값을 넣어서 응답해라!
    /**
     * 전체 목록 조회
     * GET: http://localhost:8080/api/board
     * @return ResponseEntity
     *         - 200 OK: 목록 조회 성공, 게시글 리스트 반환 (빈 리스트 포함)
     *         - 204 No Content: 조회 성공했지만 게시글이 하나도 없음
     *         - 500 Internal Server Error: 서버 내부 오류 (DB 연결 실패 등)
     */
    public ResponseEntity<List<BoardDTO>> getList() {
        log.info("============> 게시글 전체 목록 조회");

        List<BoardDTO> list = service.getList();
        return ResponseEntity.ok(list); // 200 OK + 데이터 반환
    }

    @GetMapping("/get") // ==> /api/board/get?no=1
    public BoardDTO get(@RequestParam("no") Long no){
        return service.get(no);
    }

    @ApiOperation(value = "상세정보얻기", notes = "게시글상제정보를얻는API")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "성공적으로요청이처리되었습니다.", response = BoardDTO.class),
            @ApiResponse(code = 400, message = "잘못된요청입니다."),
            @ApiResponse(code = 500, message = "서버에서오류가발생했습니다.")
    })
    @GetMapping("/get/{no}") // ==> /api/board/get/15
    public BoardDTO get2(
            @ApiParam(value = "게시글 ID", required = true, example = "1")
            @PathVariable Long no){
        return service.get(no);
    }

    @PostMapping("") // ==> /api/board + post
    public ResponseEntity<BoardDTO> create(@RequestBody BoardDTO dto){
        //@RequestBody --> 브라우저에서 보낼때도  json으로 보낼 수 있음.
        //서버에서 json을 받을 때 사용하는 어노테이션!
        log.info("🎈http 요청으로 들어온 data : {}", dto);
        //service 처리 요청
        BoardDTO boardDTO = service.create(dto);
        log.info("🎃create 메소드 실행 결과");
        //응답
        return ResponseEntity.ok(service.create(dto));
    }

}
