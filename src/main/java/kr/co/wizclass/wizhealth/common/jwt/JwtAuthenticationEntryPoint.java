package kr.co.wizclass.wizhealth.common.jwt;

import kr.co.wizclass.wizhealth.common.jwt.dto.JwtErrorResponseJson;
import kr.co.wizclass.wizhealth.exception.ErrorCode;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/***
 * 인가 : 유효한 자격증명을 제공하지 않고 접근할 때 401 Unauthorized 에러를 리턴하는 class
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException {
        // TODO : case1) token 관련 예외 공통 메세지 반환
        ErrorCode error = ErrorCode.UNAUTHORIZED;
        // TODO : case2) token 관련 예외별 메세지 반환
//        ErrorCode error = ErrorCode.get(
//                (String)request.getAttribute("exception"));

        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(error.getStatus());
        response.getWriter().println(
                        JwtErrorResponseJson.of(request, error));
    }
}
