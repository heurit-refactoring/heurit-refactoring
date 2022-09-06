package com.sj.heuritrefactoring.dto.jwt;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@AllArgsConstructor
public class TokenDto {
    private String jwtAccessToken;
    private String jwtRefreshToken;
}
