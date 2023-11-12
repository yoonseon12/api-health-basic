package study.todo.global.security;

import study.todo.global.error.exception.ErrorCode;
import study.todo.global.security.dto.JwtErrorResponseJson;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 인증 : 필요한 권한이 존재하지 않는 경우 403 Forbidden 에러를 리턴하는 class
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorCode error = ErrorCode.FORBIDDEN; // 권한 없이 접근(403)

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(error.getStatus());
        response.getWriter().println(
                JwtErrorResponseJson.of(request, error));
    }
}
