package com.busanit501.boot501.security;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Log4j2
@Service
//@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    // 스프링 시큐리티 설정 클래스에 빈으로 등록한 인스턴스 주입하기.
    private PasswordEncoder passwordEncoder;

    //생성자로 주입하기.
    public CustomUserDetailsService() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("시큐리티 loadUserByUsername 확인 : "+ username);

        //User, 스프링 시큐리티에서 제공하는 기본 클래스,
        // 사용자 가 만든 User 가 아니다. 우리는 Member 사용할 예정.

        UserDetails userDetails = User.builder()
                .username("lsy")
                .password(passwordEncoder.encode("123456"))
                // 더미 데이터, 더미 권한.
                .authorities("ROLE_USER")
//                .authorities("ROLE_ADMIN")
                .build();

        return userDetails;
    }
}
