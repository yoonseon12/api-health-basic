package kr.co.wizclass.wizhealth.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class GlobalInterceptorHandler implements HandlerInterceptor {
    private static final String LOG_ID = "UUID";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uuid = UUID.randomUUID().toString();
        request.setAttribute(LOG_ID, uuid);
        System.out.println("interceptor.preHandle :: 컨트롤러 호출전 :: "+uuid);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("interceptor.postHandle :: 컨트롤러 호출후 :: "+request.getAttribute(LOG_ID));
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("interceptor.afterCompletion :: 컨트롤러 응답 전송 후 :: "+request.getAttribute(LOG_ID));
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
