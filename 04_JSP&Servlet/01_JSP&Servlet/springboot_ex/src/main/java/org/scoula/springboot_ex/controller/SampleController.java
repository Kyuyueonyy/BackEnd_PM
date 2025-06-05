package org.scoula.springboot_ex.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleController {

    @RequestMapping (value="/home")
    public String home() {

        System.out.println("컨트롤러 동작🐾");
        return "index";
    }
}
