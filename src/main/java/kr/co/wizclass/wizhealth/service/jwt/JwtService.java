package kr.co.wizclass.wizhealth.service.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import kr.co.wizclass.wizhealth.exception.CustomException;
import kr.co.wizclass.wizhealth.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;

@Slf4j
@Service
public class JwtService {
    private static final String AUTHORITIES_KEY = "auth";
    @Value("${spring.security.jwt.secret}")
    String secret;
    @Value("${spring.security.jwt.access-token-validity-in-seconds}")
    long accessTokenValidityInMilliseconds;

    public Map<String, String> refresh(Map<String, String> refreshTokenMap) {
        String refreshToken = refreshTokenMap.get("refreshToken");
        String sub = null, roles = null;
        try {
            Jws<Claims> claims = Jwts.parser()
                    .setSigningKey(this.secret)
                    .parseClaimsJws(refreshToken);

            System.out.println();

            if (!claims.getBody().getExpiration().before(new Date())) {
                sub = String.valueOf(claims.getBody().get("sub"));
                roles = String.valueOf(claims.getBody().get("auth"));
            }

        } catch (ExpiredJwtException e) {
            log.info("만료된 Refresh 토큰 : 재 로그인 필요");
            throw new CustomException(ErrorCode.UNAUTHORIZED);
        }
        return Map.of("accessToken", createAccessToken(sub, roles));
    }

    private String createAccessToken(String sub, String roles) {
        // TODO : 확인필요 : refresh 토큰을 재발급할 때마다 사용자의 모든 이전의 토큰정보 삭제해야할까? -> 다중 프로덕트일 경우에는 ?
        SecretKey secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        Date now = new Date();
        return Jwts.builder()
                .setSubject(sub)
                .claim(AUTHORITIES_KEY, roles)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + this.accessTokenValidityInMilliseconds))
                .signWith(secretKey, SignatureAlgorithm.HS512)
                .compact();
    }
}
