package org.scoula.config;

import org.scoula.domain.Parrot;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProjectConfig2 {
    //설정값이 다른 동일한 클래스의 객체를 싱글톤으로 여러개 만들어야 하는 경우
    //
    @Bean
    public Parrot parrot1() {
        Parrot p = new Parrot();
        p.setName("Koko");
        return p;
    }

    //설정이 다른 Parrot객체를 여러개 만들어야 하는 경우
    //기본으로는 메서드 이름이 객체 이름이 됨.
    //기본) Parrot parrot1 = new Parrot();

    //별도로 설정도 가능
    //별도 설정) @Bean(name="miki")

    @Bean(name = "miki") //생성된 객체 이름 지정 가능
    public Parrot parrot2() {
        Parrot p = new Parrot();
        p.setName("Micky");
        return p;
    }

    @Bean(name = "riki")
    Parrot parrot3() {
        var p = new Parrot();
        p.setName("Riki");
        return p;
    }
}
