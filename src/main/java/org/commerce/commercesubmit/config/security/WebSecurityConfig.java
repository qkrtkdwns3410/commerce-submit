package org.commerce.commercesubmit.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * packageName    : org.commerce.commercesubmit.config.security
 * fileName       : WebsecurityConfig
 * author         : ipeac
 * date           : 24. 4. 23.
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 24. 4. 23.        ipeac       최초 생성
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // REST API 사용 시 CSRF 보호를 비활성화
                .authorizeRequests(authorizeRequests ->
                        authorizeRequests
                                .antMatchers("/api/user/**").permitAll() // 인증이 필요 없는 엔드포인트 설정
                                .anyRequest().authenticated() // 그 외 모든 요청은 인증 필요
                )
                .httpBasic(); // 기본 인증 사용
        return http.build();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}