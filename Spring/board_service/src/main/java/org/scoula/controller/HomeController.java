package org.scoula.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Log4j2
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("================> HomController /");
//        return "index";		// View의 이름
        return "redirect:/board/list";
        //list.jsp에는 db에서 전체검색 한 결과를 넣어야함
        //컨트롤러를 다시 호출하게 해야함.
        //controller -> service -> dao에 걸쳐서 db 결과를 가지고 오게 됨

        //redirect: -> 뷰로 안가고, 다시 컨트롤러로 가게 함
    }

}
