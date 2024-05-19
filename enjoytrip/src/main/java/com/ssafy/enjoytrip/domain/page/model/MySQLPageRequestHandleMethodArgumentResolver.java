package com.ssafy.enjoytrip.domain.page.model;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

public class MySQLPageRequestHandleMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private static final String DEFAULT_PARAMETER_PAGE = "page"; // 페이지 번호를 나타내는 기본 파라미터 이름
    private static final String DEFAULT_PARAMETER_SIZE = "size"; // 페이지 크기를 나타내는 기본 파라미터 이름
    private static final int DEFAULT_SIZE = 20; // 기본 페이지 크기

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        int page = parseIntOrDefault(request.getParameter(DEFAULT_PARAMETER_PAGE), 1); // 페이지 번호 파라미터를 정수로 변환, 기본값은 1
        int size = parseIntOrDefault(request.getParameter(DEFAULT_PARAMETER_SIZE), DEFAULT_SIZE); // 페이지 크기 파라미터를 정수로 변환, 기본값은 10
        PageRequest pageRequest = new PageRequest(page, size, 0, 0);
        pageRequest.calculateOffset();
        return pageRequest;
    }

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return PageRequest.class.isAssignableFrom(methodParameter.getParameterType()); // PageRequest 타입을 지원하는지 확인
    }

    private int parseIntOrDefault(String parameter, int defaultValue) {
        try {
            return parameter != null ? Integer.parseInt(parameter) : defaultValue; // 파라미터가 null이 아니면 정수로 변환, null이면 기본값 반환
        } catch (NumberFormatException e) {
            return defaultValue; // 숫자 형식이 아니면 기본값 반환
        }
    }
}
