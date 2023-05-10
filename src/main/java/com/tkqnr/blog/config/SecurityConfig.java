package com.tkqnr.blog.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//빈 등록: 스프링 컨테이너에서 객체를 관리할 수 있게 하는것
@Configuration //IoC
public class SecurityConfig {

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf 토큰 비활성화(테스트시 걸어두는게 좋음)
                .authorizeRequests()
                .antMatchers("/","/auth/**", "/js/**", "/css/**","/image/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/loginForm");
        return http.build();
    }
}