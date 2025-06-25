package org.scoula.security.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

//JWT를 사용할 때 필요한 메서드들을 만들어놓기 위한 클래스
@Component
@Log4j2
public class JwtProcessor {
    //토큰 만료 시간(5분)
    static private final long TOKEN_VALID_MILLISECOND = 1000L * 60 * 5;

    //토큰 생성 시 필요한 secretKey
    private final String secretkey = "KB_IT's YOUR LIFE_6기_19회차_JWT수업";
    //이 secretkey 문자열을 byte배열로 변환해서 넣어줘야함
    private final Key key = Keys.hmacShaKeyFor(secretkey.getBytes(StandardCharsets.UTF_8));

    //토큰 생성하기
    //subject : 사용자 (username)
    public String generateToken(String subject, String role) {
        return Jwts.builder()
                .setSubject(subject) //사용자 식별자
                .setIssuedAt(new Date()) //발급시간
                .setExpiration(new Date(new Date().getTime() + TOKEN_VALID_MILLISECOND)) //만료시간(현재시간에 토큰 만료시간(5분)을 넣어서)
                .claim("role", role) // 커스텀 claim 넣는 방법
                .signWith(key) //서명
                .compact(); //문자열 생성
    }

    //토큰 생성하기
    //subject : 사용자 (username)
    public String generateTokenByLogin(String subject) {
        return Jwts.builder()
                .setSubject(subject) //사용자 식별자
                .setIssuedAt(new Date()) //발급시간
                .setExpiration(new Date(new Date().getTime() + TOKEN_VALID_MILLISECOND)) //만료시간(현재시간에 토큰 만료시간(5분)을 넣어서)
//                .claim("role", role) // 커스텀 claim 넣는 방법
                .signWith(key) //서명
                .compact(); //문자열 생성
    }

    //검증
    //subject -> 사용자 식별자 (사용자 정보 중 고유한 값)
    public String getUserName(String token) {

        //ParserBuilder를 이용해 JwtParser 객체 생성
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(key)
                .build();

        //토큰 파싱 JWS(JSON Web Signature) (JWT -> 서명된 상태 Signed Token) 구조
        Jws<Claims> jwsClaims = jwtParser.parseClaimsJws(token);

        //Claims (Payload)부분
        Claims claims = jwsClaims.getBody();

        //Claims 에서 subject 필드 가져오기
        String username = claims.getSubject();

        return username;
    }

    /**
     * JWT Subject(role) 추출
     *
     * @param token JWT 토큰
     * @return 사용자명
     * @throws JwtException 토큰 해석 불가 시 예외 발생
     */
    public String getRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }


    /**
     * JWT 검증 (유효 기간 및 서명 검증)
     *
     * @param token JWT 토큰
     * @return 검증 결과 (true: 유효, false: 무효)
     */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            //parseClaimsJws -> 서명(Signature) exp(만료일) 검증해줌
            //사용자 정의 Claim -> 개발자가 따로 검증로직을 만들어주어야함.
            return true;
        } catch (Exception e) {
            log.error("JWT 검증 실패: {}", e.getMessage());
            return false;
        }
    }
}

