package com.kcc.security1.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//시큐리티와 관련된 설정 파일의 역할
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true,prePostEnabled = true)
public class SecurityConfig {

    private static final String[] WHITE_LIST = {
            "/",
            "/common/**",
            "/WEB-INF/views/**",
            "/join",
            "/loginForm",
            "/h2-console/**",
            "/joinForm",
            "/join"

    };

    // 비밀번호 암호화
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(authorize ->
                authorize.requestMatchers(WHITE_LIST).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated()
                ).csrf(csrf ->csrf.ignoringRequestMatchers(PathRequest.toH2Console()))
                .headers(headers ->headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin))

        .formLogin(form -> form.loginPage("/loginForm")
                //로그인에서 성공하면 어떻게할지 -> 메인 url 요청
                .loginProcessingUrl("/login").defaultSuccessUrl("/main"));
        return http.build();
    }
}
