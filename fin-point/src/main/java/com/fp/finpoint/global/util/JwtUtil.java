package com.fp.finpoint.global.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fp.finpoint.global.exception.BusinessLogicException;
import com.fp.finpoint.global.exception.ExceptionCode;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;


@Slf4j
@Component
@RequiredArgsConstructor
public class JwtUtil {
    private static byte[] secretKey;
    public static final String PREFIX = "Bearer";
    public static final String AUTHORIZATION = "Authorization";
    public static final String REFRESH = "Refresh";
    public static final String EMAIL = "email";
    public static final String SUBJECT = "finPoint token";
    public static final String SEQUENCE = "Sequence";
    private static final long MILLIS_PER_MINUTE = 60 * 1000;
    private static final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;
    private static final int ACCESS_TOKEN_VALIDATION_MINUTE = 30;  //30분
    private static final int REFRESH_TOKEN_VALIDATION_Day = 100;  // 100일

    @Value("${jwt.salt}")
    public void setSecretKey(String key) {
        byte[] secretBytes = key.getBytes();
        if (secretBytes.length < 64) {
            throw new IllegalArgumentException("secretKey must be at least 64 bytes for HS512 algorithm");
        }
        secretKey = secretBytes;
    }

    public static String createAccessToken(String email) {
        long expirationTimeMills = ACCESS_TOKEN_VALIDATION_MINUTE * MILLIS_PER_MINUTE;

        String accessToken = PREFIX + JWT.create()
                .withIssuer("https://www.pinpoint.com")
                .withSubject(SUBJECT)
                .withExpiresAt(new Date(System.currentTimeMillis() + expirationTimeMills))
                .withClaim(EMAIL, email)
                .sign(Algorithm.HMAC512(secretKey));

        log.info("# Access Token Create Successful!");
        return accessToken;
    }

    public static String createRefreshToken() {
        long expirationTimeMills = REFRESH_TOKEN_VALIDATION_Day * MILLIS_PER_DAY;

        String refreshToken = Jwts.builder()
                .setIssuer("https://www.pinpoint.com")
                .setSubject(SUBJECT)
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMills))
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS512)
                .compact();

        log.info("# Refresh Token Create Successful!");
        return refreshToken;
    }

    public static String getEmail(String token) {
        String decode = URLDecoder.decode(token, StandardCharsets.UTF_8);
        String accessToken = decode.replace(PREFIX, "");
        return JWT.decode(accessToken).getClaim(EMAIL).asString();
    }

    public static void validateAccessToken(String bearerToken) {
        String accessToken = bearerToken.substring(6, bearerToken.length());
        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build().parseClaimsJws(accessToken);
        } catch (SignatureException e) {
            throw new BusinessLogicException(ExceptionCode.TOKEN_NOT_MATCH_KEY);
        } catch (ExpiredJwtException e) {
            throw new BusinessLogicException(ExceptionCode.TOKEN_EXPIRED);
        } catch (MalformedJwtException e) {
            throw new BusinessLogicException(ExceptionCode.TOKEN_MALFORMED);
        } catch (UnsupportedJwtException e) {
            throw new BusinessLogicException(ExceptionCode.MEMBER_WRONG_CODE);
        } catch (IllegalArgumentException e) {
            throw new BusinessLogicException(ExceptionCode.TOKEN_NOT_FOUND);
        }
    }

    public static void validateRefreshToken(String email, String refreshToken) {
        String refreshTokenInRedis = RedisUtil.getRedisValue(email);

        if (refreshTokenInRedis == null || !refreshTokenInRedis.equals(refreshToken)) {
            throw new BusinessLogicException(ExceptionCode.REFRESH_INVALID);
        }

        try {
            Jwts.parserBuilder()
                    .setSigningKey(secretKey)
                    .build().parseClaimsJws(refreshToken);
        } catch (ExpiredJwtException e) {
            throw new BusinessLogicException(ExceptionCode.TOKEN_EXPIRED);
        } catch (JwtException e) {
            throw new BusinessLogicException(ExceptionCode.REFRESH_INVALID);
        }
    }
}