package com.moviereservation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/movies/addMovie").hasRole("ADMIN")
                        .requestMatchers("/member/login").permitAll()
                        .requestMatchers("/member/register").permitAll()
                        .requestMatchers("/member/*").authenticated()
                        .anyRequest().permitAll()
                )
                .formLogin((form) -> form
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .loginPage("/member/login")
                        .permitAll()
                )
                .logout((logout) -> logout.permitAll().logoutSuccessUrl("/"))
                .exceptionHandling()
                //로그인 안되었을 때 리다이렉트 처리
                .authenticationEntryPoint(((request, response, authException) ->
                        response.sendRedirect("/member/login")))
                //로그인은 되었지만 권한이 맞지 않을 때 인증 처리
                .accessDeniedHandler(((request, response, accessDeniedException) ->
                        response.sendRedirect("/")));

        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery("select member_id, member_password, enable "
                        + "from members "
                        + "where member_id = ?")
                .authoritiesByUsernameQuery("select member_id, role_name "
                        + "from members "
                        + "where member_id = ?");
    }

    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

