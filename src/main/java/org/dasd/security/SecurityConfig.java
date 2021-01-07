package org.dasd.security;


import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Log
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private DasdUserService dasdUserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {//웹 자원에 대한 보안을 확인하는 메소드
        log.info("security config...");

        //특정한 경로에 특정한 권한을 가진 사용자만 접근할 수 있도록 설정
        http.authorizeRequests().antMatchers("/guest/**").permitAll();
        http.authorizeRequests().antMatchers("/manager/**").hasRole("MANAGER");

        http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");

        http.formLogin().loginPage("/login");

        http.exceptionHandling().accessDeniedPage("/accessDenied");//접근 권한 없음 페이지 처리
        
        http.logout().logoutUrl("/logout").invalidateHttpSession(true);//세션 무효화 처리

        http.rememberMe()
                .key("dasdkey")
                .userDetailsService(dasdUserService)
        .tokenRepository(getJDBCRepository())
        .tokenValiditySeconds(60*60*24);

    }

    private PersistentTokenRepository getJDBCRepository(){//데이터 베이스 기반 remember-me 토큰 생성기 반환
        JdbcTokenRepositoryImpl repo=new JdbcTokenRepositoryImpl();
        repo.setDataSource(dataSource);
        return repo;
        
    }


}
