package org.scoula.advice;

//Advice : 어떤 부가기능을, 언제 사용할지 정의해둠
/* 어떤 타켓이 되는 클래스의 메서드를 호출해서 실행할 때
* 어떤 부가적인 기능을 넣을지 충고해주는 클래스
* 클래스 이름 위에 @Aspect
* */

import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect //관심사(ADVICE)들을 모아놓은 클래스라는것을 의미
@Log4j2
@Component //싱글톤으로
public class LogAdvice {

    @Before("execution(* org.scoula.sample.service.SampleService*.*(..))") //메소드 호출전에
    public void logBefore() {
        log.info("==========================");
    }

    //부가적인 기능을 넣을 타겟이 되는 SampleService클래스 안에 있는
    //조인 포인트가 되는 모든 메서드를 호출해서 실행하기 전에 (이벤트라고 인식함)
    //logBefore() 메서드를 먼저 호출해라! 설정
    // ==> 이벤트가 발생하면 aop proxy 객체가 logBefore() 메서드를 먼저 호출함.

    /* 스프링에서 사용된 설계 기법 3가지 : 24~26개 정도의 설계 패턴 정리
    * Proxy(대리인) 설계 패턴 (Proxy Design Pattern) : AOP
    * 싱글톤 디자인 패턴
    * 팩토리 디자인 패턴
    * */

    @Before("execution(* org.scoula.sample.service.SampleService*.doAdd(String, String)) && args(str1, str2)")
    public void logBeforeWithParam(String str1, String str2) {
        log.info("str1:" + str1);
        log.info("str2:" + str2);
    }
}
