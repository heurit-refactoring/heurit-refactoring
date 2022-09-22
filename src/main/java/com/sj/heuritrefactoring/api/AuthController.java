package com.sj.heuritrefactoring.api;

import com.sj.heuritrefactoring.dto.user.UserResponseDto;
import com.sj.heuritrefactoring.service.AuthService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
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


    @ApiOperation(value = "카카오 로그인", notes = "카카오 로그인 입니다.")
    @ApiImplicitParams({
            @ApiImplicitParam(
                    name = "accessToken",
                    value = "사용자 accessToken"
            )
    })
    @PostMapping(value = "/kakao")
    public ResponseEntity<UserResponseDto> kakaoLogin(@RequestHeader("oauthToken") String accessToken, HttpServletResponse res) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.createToken(accessToken, res));
    }
}
