package study.todo.global.config;


import study.todo.global.interceptor.GlobalInterceptorHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(globalInterceptorHandler())
                .order(1) // 인터셉터 호출 순서. 낮을수록 먼저 호출
                .addPathPatterns("/**") // 인터셉터 적용할 URL 패턴 지정
                .excludePathPatterns("/css/**", "/*.ico", "/error"); // 인터셉터 제외 경로
    }

    @Bean
    public GlobalInterceptorHandler globalInterceptorHandler() {
        return new GlobalInterceptorHandler();
    }
}
