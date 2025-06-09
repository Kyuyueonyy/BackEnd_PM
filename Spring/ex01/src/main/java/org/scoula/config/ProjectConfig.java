package org.scoula.config;

import org.scoula.domain.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 객체 생성 시 설정파일로 2가지 중 하나를 사용함
/* 1. xml파일
 * 2. java 파일(***)*/

@Configuration //설정파일로 스프링 프레임워크에 알려주는 역할
public class ProjectConfig {
    @Bean //스프링에 싱글톤으로 만들어야한다고 알려주는 어노테이션
    //이 함수는 스프링이 불러서 싱글톤으로 만듦
    //내가 만든 클래스는 클래스 이름 위에 @Bean이라고 하면 싱글톤으로 만들어줌

    //그럼 왜 Configuraiton 안에 넣을까?
    //내가 만든 클래스가 아닌 경우!
    //클래스를 열어서 @Bean을 붙여줄 수가 없음.
    //이런 경우 Config파일에서 객체를 생성하고 조립하게 해야함
    public Parrot parrot() {
        Parrot p = new Parrot(); //내가 아닌, 스프링이 이 함수를 불러서 객체 생성 후 스프링에 등록
        p.setName("Koko");
        return p;
        //스프링이 싱글톤으로 만들어서 관리(공장처럼! - 팩토리)
    }

    @Bean
    String hello() {
        return "Hello";
    }

    @Bean
    Integer ten() {
        return 10;
    }
}
