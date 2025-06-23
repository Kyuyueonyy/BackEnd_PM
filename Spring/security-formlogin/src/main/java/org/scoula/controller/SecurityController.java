package org.scoula.controller;

/*
○ /security/all 접속시 로그 출력 및 sercurity/all 뷰로 이동
○ /security/member 접속시 로그 출력 및 sercurity/member 뷰로 이동
○ /security/admin 접속시 로그 출력 및 sercurity/admin 뷰로 이동
○ all, member, admin 뷰 jsp 작성
*/

import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.domain.CustomUser;
import org.scoula.security.account.domain.MemberVO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/security")
@Log4j2
public class SecurityController {
    @GetMapping("/all")
    public void doAll() {
        // /security/all 접속시 로그 출력 및 sercurity/all 뷰로 이동
        log.info("비회원, 회원 모두 접근 가능 페이지");
    }

    @GetMapping("/member")
    public void domember() {
        // /security/member 접속시 로그 출력 및 sercurity/member 뷰로 이동
        log.info("회원만 접근 가능 페이지");
    }

    @GetMapping("/admin")
    public void doAdmin() {
        // /security/admin 접속시 로그 출력 및 sercurity/admin 뷰로 이동
        log.info("관리자만 접근 가능 페이지");
    }

    @GetMapping("/login")
    public void doLogin() {
        log.info("로그인 페이지로 전환");
    }

    @GetMapping("/logout")
    public void doLogout() {
        log.info("로그아웃 페이지로 전환");
    }

    /*
    * principal 주입
    * authentication 주입
    * authenticationPrincipal 주입*/

    //Principal 사용
    //가장 단순한 방식 -> 로그인한 사용자의 username만 필요할 때 사용
    @GetMapping("/member/principal")
    public void PrintUserDetailByPrincipal(Principal principal) {
        log.info("username ==========> {}", principal.getName());
    }

    //Authentication
    //사용자 이름 뿐 아니라, 권한, 인증상태, 자격 증명 등 접근 가능
    @GetMapping("/member/Authentication")
    public void PrintUserDetailByAuthentication(Authentication authentication) {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        log.info("username ==========> {}", userDetails.getUsername());
    }


    //@AuthenticationPrincipal
    //SpringSecurity가 authentication.getPrincipal에서 꺼낸 객체를 직접 주입
    //-> CustomUserDetail (구현체)에 접근 가능 (CustomUser)
    @GetMapping("/member/authentication-principal")
    public void PrintUserDetailByAuthenticationPrincipal(
            @AuthenticationPrincipal CustomUser customUser) {
        MemberVO memberVO = customUser.getMember();
        log.info("username ==========> {}", memberVO);
    }

}
