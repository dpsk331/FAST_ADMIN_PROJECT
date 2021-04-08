package com.example.study.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration     // 설정 파일 선언
@EnableJpaAuditing // 감사로 활성화
public class JpaConfig {
}
