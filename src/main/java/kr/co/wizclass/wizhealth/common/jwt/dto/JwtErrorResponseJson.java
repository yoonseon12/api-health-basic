package kr.co.wizclass.wizhealth.common.jwt.dto;

import kr.co.wizclass.wizhealth.exception.ErrorCode;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;

public class JwtErrorResponseJson {
    public static JSONObject of(HttpServletRequest request, ErrorCode errorCode) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("resultCode", "FAIL");
        jsonObject.put("path"      , request.getRequestURI());
        jsonObject.put("httpMethod", request.getMethod());
        jsonObject.put("status"    , errorCode.getStatus());
        jsonObject.put("timestamp" , LocalDateTime.now());
        jsonObject.put("message"   , errorCode.getMessage());

        return jsonObject;
    }
}
