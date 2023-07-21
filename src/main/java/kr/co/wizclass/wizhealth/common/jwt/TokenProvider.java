package kr.co.wizclass.wizhealth.common.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import kr.co.wizclass.wizhealth.common.jwt.dto.TokenDTO;
import kr.co.wizclass.wizhealth.domain.entity.RefreshToken;
import kr.co.wizclass.wizhealth.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletRequest;
import java.security.Key;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/***
 * Token의 생성, 인증정보 조회, 유효성 검증, 암호화 설정 등의 역할을 하는 클래스
 */
@Slf4j
@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TokenProvider implements InitializingBean {
    private final RefreshTokenRepository refreshTokenRepository;
    private final String AUTHORITIES_KEY = "auth";
    private Key key;
    @Value("${spring.security.jwt.secret}")
    private String secret;
    @Value("${spring.security.jwt.access-token-validity-in-seconds}")
    private long accessTokenValidityInSeconds;
    @Value("${spring.security.jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenValidityInSeconds;

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /** token 생성 algorithm **/
    public TokenDTO createToken(Authentication authentication) {
        Date now = new Date();
        String roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return TokenDTO.builder()
                .accessToken(createAccessToken(authentication.getName(), roles))
                .refreshToken(createRefreshToken(authentication.getName(), roles))
                .build();
    }

    /** Access Token 발급 **/
    public String createAccessToken(String sub, String roles) {
        LocalDateTime localDateTime = LocalDateTime.now()
                .plusSeconds(accessTokenValidityInSeconds);
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date expirationDate = Date.from(instant);
        return Jwts.builder()
                .setSubject(sub)
                .claim(AUTHORITIES_KEY, roles) // 권한
                .setExpiration(expirationDate) // 만료일시
                .signWith(key, SignatureAlgorithm.HS512)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .compact();
    }

    /** Refresh Token 발급 **/
    @Transactional
    public String createRefreshToken(String sub, String roles) {
        expirationRefreshToken(sub);

        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        LocalDateTime now = LocalDateTime.now();
        RefreshToken refreshToken = RefreshToken.builder()
                .refreshToken(uuid)
                .email(sub)
                .roles(roles)
                .loginDate(now)
                .issuedDate(now)
                .expirationDate(now.plusSeconds(refreshTokenValidityInSeconds))
                .tokenStatus(TokenStatus.OPEN)
                .build();

        return refreshTokenRepository.save(refreshToken).getRefreshToken();
    }

    /** Refresh Token 만료 **/
    @Transactional
    public void expirationRefreshToken(String email) {
        Boolean isEmailExists = refreshTokenRepository.existsByEmail(email);
        if (isEmailExists) {
            refreshTokenRepository.updateExpirationByEmail(email);
        }
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
