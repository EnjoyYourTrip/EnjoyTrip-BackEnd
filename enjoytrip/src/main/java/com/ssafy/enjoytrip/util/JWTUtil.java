package com.ssafy.enjoytrip.util;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

import com.ssafy.enjoytrip.exception.UnAuthorizedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JWTUtil {

    @Value("${jwt.salt}")
    private String salt;

    @Value("${jwt.access-token.expiretime}")
    private long accessTokenExpireTime;

    @Value("${jwt.refresh-token.expiretime}")
    private long refreshTokenExpireTime;

    public String createAccessToken(Long memberId) { // AccessToken 생성
        return create(memberId, "access-token", accessTokenExpireTime);
    }

    // RefreshToken 생성 (유효기간이 AccessToken 보다 더 길게 설정됨)
    public String createRefreshToken(Long memberId) {
        return create(memberId, "refresh-token", refreshTokenExpireTime);
    }

    //	Token 발급
//		key : Claim에 셋팅될 key 값
//		value : Claim에 셋팅 될 data 값
//		subject : payload에 sub의 value로 들어갈 subject값
//		expire : 토큰 유효기간 설정을 위한 값
//		jwt 토큰의 구성 : header + payload + signature

    /**
     * 토큰 발급 메서드
     *
     * @param memberId   유저 아이디
     * @param subject    토큰 제목
     * @param expireTime 토큰 유효기간
     * @return 생성된 JWT 토큰
     */
    private String create(Long memberId, String subject, long expireTime) {
        // Payload 설정 : 생성일 (IssuedAt), 유효기간 (Expiration), 토큰 제목 (Subject), 데이터 (Claim) 등 정보 세팅.
        Claims claims = Jwts.claims()
                .setSubject(subject) //  // 토큰 제목 설정 (ex: access-token, refresh-token)
                .setIssuedAt(new Date()) // 생성일 설정
                .setExpiration(new Date(System.currentTimeMillis() + expireTime));// 만료일 설정 (유효기간)

        // 데이터 저장 (key: "memberId", value: memberId)
        claims.put("memberId", memberId);

        String jwt = Jwts.builder()
                .setHeaderParam("typ", "JWT") // Header 설정 : 토큰의 타입, 해쉬 알고리즘 정보 세팅.
                .setClaims(claims) // Payload 설정
                .signWith(SignatureAlgorithm.HS256, this.generateKey()) // Signature 설정 : secret key를 활용한 암호화.
                .compact(); // 직렬화 처리.
        log.debug("Generated JWT for memberId {}: {}", memberId, jwt); // 로그 추가

        return jwt;
    }

    //	Signature 설정에 들어갈 key 생성.

    /**
     * Signature 설정에 사용할 key 생성
     *
     * @return 생성된 key
     */
    private byte[] generateKey() {
        byte[] key = null;
        try {
//			charset 설정 안하면 사용자 플랫폼의 기본 인코딩 설정으로 인코딩 됨.
            key = salt.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            log.error("Making JWT Key Error ::: {}", e.getMessage(), e);
        }
        return key;
    }

    //	전달 받은 토큰이 제대로 생성된 것인지 확인 하고 문제가 있다면 UnauthorizdException 발생.

    /**
     * 전달 받은 토큰이 유효한지 확인
     *
     * @param token 확인할 토큰
     * @return 토큰이 유효하면 true, 그렇지 않으면 false
     */
    public boolean checkToken(String token) {
        try {
            log.debug("Token to be verified: {}", token); // 로그 추가

//			Json Web Signature? 서버에서 인증을 근거로 인증 정보를 서버의 private key 서명 한것을 토큰화 한것
//			setSigningKey : JWS 서명 검증을 위한  secret key 세팅
//			parseClaimsJws : 파싱하여 원본 jws 만들기
            // JWS 서명 검증을 위한 secret key 세팅 및 파싱
//            Jws<Claims> claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(token);
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(this.generateKey())
                    .build()
                    .parseClaimsJws(token);

//			Claims 는 Map 구현체 형태
            log.debug("claims: {}", claims);
            return true;
        } catch (Exception e) {
            log.error("Invalid token: {}", e.getMessage());
            return false;
        }
    }

    /**
     * 토큰에서 memberId 추출
     *
     * @param authorization JWT 토큰
     * @return 추출된 memberId
     */
    public Long getMemberId(String authorization) {
        if (authorization.startsWith("Bearer ")) {
            authorization = authorization.substring(7); // "Bearer " 부분 제거
        }

        Jws<Claims> claims = null;
        try {
            // JWT 토큰 파싱 및 검증
//            claims = Jwts.parser().setSigningKey(this.generateKey()).parseClaimsJws(authorization);
            claims = Jwts.parserBuilder()
                    .setSigningKey(this.generateKey())
                    .build()
                    .parseClaimsJws(authorization);
        } catch (Exception e) {
            log.error("Failed to parse token: {}", e.getMessage());
            throw new UnAuthorizedException();
        }

        // Claims 는 Map 구현체 형태
        Map<String, Object> value = claims.getBody();
        log.info("value : {}", value);
        return ((Number) value.get("memberId")).longValue();
    }

}
