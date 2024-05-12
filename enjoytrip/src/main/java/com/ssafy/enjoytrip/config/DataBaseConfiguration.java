package com.ssafy.enjoytrip.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:/application.properties") // application.properties 파일에서 속성을 로드
@MapperScan(basePackages = { "com.ssafy.enjoytrip.domain.*.mapper" }) // MyBatis 매퍼 스캔 경로 지정
public class DataBaseConfiguration {

    final ApplicationContext applicationContext;

    public DataBaseConfiguration(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari") // HikariCP 데이터베이스 연결 풀의 설정을 로드
    public HikariConfig hikariConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource dataSource() { // HikariCP 데이터베이스 연결 풀을 설정한 DataSource 빈을 생성
        return new HikariDataSource(hikariConfig());
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception { // MyBatis의 SqlSessionFactory 빈을 생성
        SqlSessionFactoryBean session = new SqlSessionFactoryBean();
        org.apache.ibatis.session.Configuration mybatisConfig = new org.apache.ibatis.session.Configuration();
        mybatisConfig.setMapUnderscoreToCamelCase(true); // snake_case를 camelCase로 매핑 활성화
        session.setConfiguration(mybatisConfig); // MyBatis 설정 추가

        session.setDataSource(dataSource); // 데이터 소스 설정
        session.setMapperLocations(applicationContext.getResources("classpath:mappers/**/*.xml")); // MyBatis 매퍼 파일의 위치 지정
        session.setTypeAliasesPackage("com.ssafy.enjoytrip.domain.*.model"); // 타입 별칭 패키지 지정
        return session.getObject(); // SqlSessionFactory 객체 반환
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) { // SqlSessionTemplate 빈을 생성
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}