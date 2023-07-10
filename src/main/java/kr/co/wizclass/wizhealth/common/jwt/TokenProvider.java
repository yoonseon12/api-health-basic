package kr.co.wizclass.wizhealth.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import kr.co.wizclass.wizhealth.common.jwt.dto.TokenDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/***
 * Token의 생성, 인증정보 조회, 유효성 검증, 암호화 설정 등의 역할을 하는 클래스
 */
@Slf4j
@Component
public class TokenProvider implements InitializingBean {
    private static final String AUTHORITIES_KEY = "auth";
    private final String secret;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private Key key;

    public TokenProvider(@Value("${spring.security.jwt.secret}") String secret,
                         @Value("${spring.security.jwt.access-token-validity-in-seconds}") long accessTokenValidityInMilliseconds,
                         @Value("${spring.security.jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInMilliseconds) {
        this.secret = secret;
        this.accessTokenValidityInMilliseconds = accessTokenValidityInMilliseconds;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInMilliseconds;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /** token 생성 algorithm **/
    public TokenDTO createToken(Authentication authentication) {
        Date now = new Date();
        return TokenDTO.builder()
                .accessToken(createAccessToken(authentication, now))
                .refreshToken(createRefreshToken(authentication, now))
                .build();
    }

    /** Access Token 발급 **/
    public String createAccessToken(Authentication authentication, Date now) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + this.accessTokenValidityInMilliseconds))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /** Refresh Token 발급 **/
    public String createRefreshToken(Authentication authentication, Date now) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .setIssuedAt(now) // 발급시간
                .setExpiration(new Date(now.getTime() + this.refreshTokenValidityInMilliseconds)) // 만료시간
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    /** 인증 정보 조회 **/
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        List<? extends GrantedAuthority> authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        User principal = new User(claims.getSubject(), "", authorities);

        return new UsernamePasswordAuthenticationToken(principal, token, authorities);
    }

    /** 토큰 유효성 검증을 수행 **/
    public boolean validateToken(String token, ServletRequest request) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException e) {
            log.info("잘못된 JWT 서명입니다");
//            request.setAttribute("exception", ErrorCode.INVALID_TOKEN.name());
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다");
//            request.setAttribute("exception", ErrorCode.EXPIRED_TOKEN.name());
        } catch (UnsupportedJwtException e) {
            log.info("지원하지 않는 JWT 토큰입니다");
//            request.setAttribute("exception", ErrorCode.UNAUTHORIZED_TOKEN.name());
        } catch (IllegalArgumentException e) {
            log.info("잘못된 JWT 토큰입니다");
//            request.setAttribute("exception", ErrorCode.WRONG_TOKEN.name());
        }
        return false;
    }
}
