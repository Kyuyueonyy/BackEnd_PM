package org.scoula.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.board.domain.BoardAttachmentVO;
import org.scoula.board.dto.BoardDTO;
import org.scoula.board.service.BoardService;
import org.scoula.common.util.UploadFiles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.File;

@Log4j2
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor //생성자 주입해달라는 의미
public class BoardController {

    private final BoardService service;

    /* service 불러올 때 생성자 주입 방식*/
/*    public BoardController(BoardService service) {
        this.service = service;
    }*/

    /* 요청 하나 당 함수 하나씩 작성! */
    @GetMapping("/list") //board/list
    public void list(Model model) {
        //db에서 가지고 온 것 있어야함
        //Controller -> Service -> DAO
        log.info("=================> BoardController /list");
        model.addAttribute("list", service.getList());
        //요청한 주소와 views의 호출할 파일명이 같으면 return 안해도 됨(void함수)
    }

//    @GetMapping("/get") //board/get

    // 등록 폼 표시
    @GetMapping("/create")
    public void create() {
        log.info("create");
        // 뷰 이름: "board/create"
    }

    // 상세 조회
    @GetMapping({ "/get", "/update" })  // 두 개의 URL을 같은 메서드에서 처리
    public void get(@RequestParam("no") Long no, Model model) {
        log.info("/get or update");
        model.addAttribute("board", service.get(no)); // 특정 게시글 조회

        // URL에 따라 뷰 이름 결정: "board/get" 또는 "board/update"
    }

    // 수정 처리
    @PostMapping("/update")
    public String update(BoardDTO board, RedirectAttributes ra) {
        log.info("update:" + board);
        boolean result = service.update(board);                   // 게시글 수정
        log.info("update result: " + result);

        if(result){
            ra.addFlashAttribute("message", "게시글이 수정 되었습니다.");
        }

        return "redirect:/board/list";           // 목록으로 리다이렉트
    }

    @PostMapping("/create") //board/create(입력한 내용 DB처리 해달라는 요청)
    public String create(BoardDTO board) {
        log.info("create: " + board);
        service.create(board);
        return "redirect:/board/list";
    }


    @PostMapping("/delete") //board/delete(삭제한 내용 DB처리 해달라는 요청)
    public String delete(@RequestParam("no") Long no) {
        log.info("delete..." + no);
        service.delete(no);
        return "redirect:/board/list";
    }

    @GetMapping("/download/{no}")
    @ResponseBody
    public void download(@PathVariable("no") Long no, HttpServletResponse response) throws Exception {
        //첨부파일 조회
        BoardAttachmentVO attachmentVO = service.getAttachment(no);

        File file = new File(attachmentVO.getPath());

        UploadFiles.download(response, file, attachmentVO.getFilename());

    }
}
