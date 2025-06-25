package org.scoula.security.filter;

import lombok.extern.log4j.Log4j2;
import org.scoula.security.account.dto.LoginDTO;
import org.scoula.security.handler.LoginFailureHandler;
import org.scoula.security.handler.LoginSuccessHandler;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Log4j2
public class JwtUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    //생성자 주입
    public JwtUsernamePasswordAuthenticationFilter(
            AuthenticationManager authenticationManager,
            LoginSuccessHandler loginSuccessHandler,
            LoginFailureHandler loginFailureHandler) {
        super(authenticationManager);
        setFilterProcessesUrl("/api/auth/login"); //필터 적용 주소
        setAuthenticationSuccessHandler(loginSuccessHandler); //성공했을 때 핸들러 등록
        setAuthenticationFailureHandler(loginFailureHandler); //실패 했을 때 핸들러 등록
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        //1. http body에 들어온 json -> dto(LoginDTO.of())
        LoginDTO loginDTO = LoginDTO.of(request);
        //인증해달라고 인증 매니저에게 요청
        //2. 인증매니저에게 로그인 정보를 줄 때는 Token객체를 만들어서 주어야함.
        //   인증정보 Token 만들기(DTO에 넣었던 것을 꺼내어 토큰으로 만듦)
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword());
        //3. 인증매니저에게 토큰을 주면서 인증해달라고 요청
        //   --> 인증 성공하면, Authentication 객체를 생성해서 리턴해줌
        return getAuthenticationManager().authenticate(authenticationToken);
    }
}
