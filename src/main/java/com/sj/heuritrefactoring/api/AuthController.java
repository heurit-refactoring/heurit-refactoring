package com.sj.heuritrefactoring.api;

import com.sj.heuritrefactoring.dto.user.UserResponseDto;
import com.sj.heuritrefactoring.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RequestMapping("/auth")
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/kakao")
    public ResponseEntity<UserResponseDto> kakaoLogin(@RequestHeader("oauthToken") String accessToken, HttpServletResponse res) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.createToken(accessToken, res));
    }
}
