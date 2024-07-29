package code.config.JWT;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import code.dto.UserDto;
import lombok.extern.slf4j.Slf4j;

import io.jsonwebtoken.Jwts;



@Component @Slf4j
public class JWTUtil {

    private static SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret) {

        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
    }

    public static String getNo(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("no", String.class);
    }

    public static String getName(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("Name", String.class);
    }

    public static String getEmail(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("email", String.class);
    }

    public static String getLocation(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("location", String.class);
    }

    public String getRole(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().get("role", String.class);
    }

    public UserDto tokenToDto(String token)
    {
        var attr = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload();

        return UserDto.builder()
                                .userNo(attr.get("no", Long.class))
                                .userId(attr.get("id", String.class))
                                .userName(attr.get("name", String.class))
                                .email(attr.get("email", String.class))
                                .role(attr.get("role", String.class))
                                .build();
    }

    public Boolean isExpired(String token) {

        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getPayload().getExpiration().before(new Date());
    }

    public String createJwt(String name, String role, Long expiredMs) {
        // JWT 발급
        return Jwts.builder()
                .claim("name", name)
                .claim("role", role)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
}