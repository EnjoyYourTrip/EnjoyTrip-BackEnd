package com.ssafy.enjoytrip.config;

import com.ssafy.enjoytrip.interceptor.JWTInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
@RequiredArgsConstructor
public class WebConfiguration implements WebMvcConfigurer {

//    private final JWTInterceptor jwtInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**") // 모든 경로에 대해 CORS 설정 적용
                .allowedOrigins("*") // 모든 도메인에 대해 허용
                .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(),
                        HttpMethod.DELETE.name(), HttpMethod.HEAD.name(), HttpMethod.OPTIONS.name(),
                        HttpMethod.PATCH.name()) // 허용할 HTTP 메서드 지정
                .maxAge(1800); // Pre-flight 요청 캐싱 시간(초 단위)
    }

    // 인터셉터 설정
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(jwtInterceptor)
//                .addPathPatterns("/api/**") // JWT 검증이 필요한 경로 패턴 지정
//                .excludePathPatterns("/api/auth/**"); // JWT 검증이 필요 없는 경로 패턴 지정 (예: 로그인, 회원가입)
    }

    // 정적 자원 핸들링
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**") // `/img/**` 경로로 요청되는 자원 핸들링
                .addResourceLocations("classpath:/static/assets/img/"); // 정적 자원의 실제 위치
        registry.addResourceHandler("/*.html**") // 모든 HTML 파일에 대해 자원 핸들링
                .addResourceLocations("classpath:/static/"); // 정적 자원의 실제 위치
    }

}
