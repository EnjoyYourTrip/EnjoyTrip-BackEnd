package com.ssafy.enjoytrip.interceptor;

import com.ssafy.enjoytrip.exception.UnAuthorizedException;
import com.ssafy.enjoytrip.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;


@Component
@Slf4j
@RequiredArgsConstructor
public class JWTInterceptor implements HandlerInterceptor {

    private final String HEADER_AUTH = "Authorization";

    private final JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        final String token = request.getHeader(HEADER_AUTH); // 요청 헤더에서 토큰 가져옴

        // 토큰이 존재하며 유효한 경우
        if (token != null && jwtUtil.checkToken(token)) {
            log.info("토큰 사용 가능 : {}", token);
            return true; // 요청 처리를 계속 진행
        } else {
            log.info("토큰 사용 불가능 : {}", token);
            throw new UnAuthorizedException(); // 유효하지 않은 경우 예외 발생
        }

    }
}
