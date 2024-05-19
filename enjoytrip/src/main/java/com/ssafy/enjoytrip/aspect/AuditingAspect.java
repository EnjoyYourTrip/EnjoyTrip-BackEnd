//package com.ssafy.enjoytrip.aspect;
//
//import com.ssafy.enjoytrip.domain.common.BaseTimeEntity;
//import lombok.extern.slf4j.Slf4j;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
//@Aspect
//@Component
//@Slf4j
//public class AuditingAspect {
//
//    // 모든 "Mapper"로 끝나는 모든 클래스 내의 이름이 "insert"로 시작하는 메서드의 실행 전 트리거
//    @Before("execution(* com.ssafy.enjoytrip.domain.*.mapper.*Mapper.insert*(..)) && args(entity)")
//    public void setCreatedDate(BaseTimeEntity entity) {
//        log.info("Setting created date for entity: {}", entity.toString());
//        log.info("최초 등록");
//        if (entity.getCreatedDate() == null) {
//            entity.setCreatedDate(LocalDateTime.now());
//        }
//        if (entity.getLastModifiedDate() == null) {
//            entity.setLastModifiedDate(LocalDateTime.now());
//        }
//    }
//
//    // 모든 "Mapper"로 끝나는 모든 클래스 내의 이름이 "update"로 시작하는 메서드의 실행 전 트리거
//    @Before("execution(* com.ssafy.enjoytrip.domain.*.mapper.*Mapper.update*(..)) && args(entity)")
//    public void setLastModifiedDate(BaseTimeEntity entity) {
//        log.info("Setting last modified date for entity: {}", entity.toString());
//        log.info("마지막 수정일");
//        entity.setLastModifiedDate(LocalDateTime.now());
//    }
//}