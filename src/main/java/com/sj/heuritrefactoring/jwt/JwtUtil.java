package com.sj.heuritrefactoring.jwt;

import com.sj.heuritrefactoring.dto.jwt.TokenDto;
import com.sj.heuritrefactoring.dto.user.UserInfoDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class JwtUtil {

    private final int ACCESS_TOKEN_EXP_MIN = 3600; // 30 min
    private final int REFRESH_TOKEN_EXP_MIN = 604800; // 7 day
    private final Date NOW = new Date();

    @Value("${jwt.secret}")
    private String secret;

    private SecretKey key;

    @PostConstruct
    protected void init() {
        key = Keys.hmacShaKeyFor(secret.getBytes());
    }


    public TokenDto createToken(UserInfoDto userInfoDto) {
        // createJws: JWT를 Signature로 token을 만듦.

        String accessToken = createJws(ACCESS_TOKEN_EXP_MIN, userInfoDto);
        String refreshToken = createJws(REFRESH_TOKEN_EXP_MIN, null);

        return new TokenDto(accessToken, refreshToken); // 생성자로 객체를 만들기 때문에 @Setter 제거했습니다.
    }

    private String createJws(Integer expMin, UserInfoDto userInfoDto) {
        //Header
        Map<String, Object> header = new HashMap<>();
        header.put("typ", "jwt");
        header.put("alg", "HS256");

        //Body(Claims)
        Map<String, Object> claims = new HashMap<>();
        claims.put("iss", "worryrecord");
        claims.put("issueAt", NOW);
        claims.put("exp", new Date(System.currentTimeMillis() + 1000 * 60 * expMin));

        // TODO:: null 처리 코드 확인
        if(Objects.nonNull(userInfoDto))  {
            claims.put("socialId", userInfoDto.getSocialId());
            claims.put("socialType", userInfoDto.getSocialType().toString());
            claims.put("username", userInfoDto.getUsername());
            claims.put("email", userInfoDto.getEmail());
        }

        //Signiture
        String token = Jwts.builder()
                .setHeader(header)
                .setClaims(claims)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return token;

    }
}