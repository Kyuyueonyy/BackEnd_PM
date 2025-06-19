package org.scoula.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/* STOMP 메시지 흐름
* 1. 클라이언트 연결 -> ws://localhost:8080/chat-app (소켓연결)
* 2. 토픽 구독 -> "/topic/?
* 3. 메시지 발행 -> "/app/?"
* 4. 서버에서 처리 -> Controller @MessageMapping("/?")이 붙은 핸들러 메소드 실행
* 5. 브로드캐스트(구독자들한테 전송) @SendTo("/topic/?") ?을 구독한 모든 구독자에게 전송
* */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) { //WebSocket활성화, 메시지브로커(STOMP) 기능 활성화
        // 구독시 사용할토픽접두어
        config.enableSimpleBroker("/topic");
        // 클라이언트가발행시사용해야하는접두어
        config.setApplicationDestinationPrefixes("/app");
    }

    //STOMP 엔드포인트
    //클라이언트가 WebSocket 연결을 시작할 때 사용하는 URL(HTTP 핸드쉐이크)
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chat-app")  // 접속 엔드포인트, ws://localhost:8080/chat-app
                .setAllowedOrigins("*")
                .withSockJS();
        // CORS 허용 : 모든 origin 허용
        // 운영 시 구체적인 도메인 지정을 하는게 좋음
        // .setAllowedOrigins("https://[도메인]")
        // ex) "https://naver.com"
        // .withSockJS() : websocket을 지원하지 않는 브라우저 대응
    }
}
