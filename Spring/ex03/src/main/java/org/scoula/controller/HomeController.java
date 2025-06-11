package org.scoula.controller;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

//싱글톤으로 만들어주고, 스프링에 이 클래스가 컨트롤러 역할을 하는 클래스라고 등록시켜줌
@Controller
@Log4j2
public class HomeController {

    //요청 하나당 함수 하나
    //요청이 어떻게 들어오는지 설정
    //어떤 함수를 부를지 정의함

    //스프링에서는 get 요청이 들어오면 어노테이션으로!
    @GetMapping("/") //get요청이 root("/")로 들어오면
    public String home(Model model) {
        model.addAttribute("name", "홍길동");

        return "index";
    }
}
