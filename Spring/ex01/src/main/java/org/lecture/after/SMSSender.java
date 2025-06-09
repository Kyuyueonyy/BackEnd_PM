package org.lecture.after;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary //동일한 타입의 여러 Bean 중 우선 주입(EmailSender보다 SMSSender가 먼저 주입되도록 하기 위함)

public class SMSSender extends EmailSender{
    //문자 보내는 기능이라고 가정
    @Override
    public void send(String message) {

        System.out.println("SMS 발송 : " + message);
    }
}
