package kr.co.wizclass.wizhealth.global.config;

import kr.co.wizclass.wizhealth.global.security.JwtAccessDeniedHandler;
import kr.co.wizclass.wizhealth.global.security.JwtAuthenticationEntryPoint;
import kr.co.wizclass.wizhealth.global.security.JwtSecurityConfig;
import kr.co.wizclass.wizhealth.global.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf().disable()

                /** 401, 403 Exception 핸들링 **/
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()
                .sameOrigin()

                /** 세션을 사용하지 않기 때문에 STATELESS로 설정 **/
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)

                /** HttpServletRequest를 사용하는 요청들에 대한 접근 제한 설정 **/
                .and()
                .authorizeHttpRequests()

                .antMatchers("/").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .requestMatchers(new AntPathRequestMatcher("/api/v1/users/signup")).permitAll() // 회원가입 API
                .requestMatchers(new AntPathRequestMatcher("/api/v1/auths/login")).permitAll()  // 로그인 API
                .requestMatchers(new AntPathRequestMatcher("/api/v1/auths/token")).permitAll()  // 토큰 재발급 API
                .requestMatchers(PathRequest.toH2Console()).permitAll() // H2

                .anyRequest().authenticated()

                /** JwtSecurityConfig 적용 **/
                .and()
                .apply(new JwtSecurityConfig(tokenProvider));

        return httpSecurity.build();
    }
}
