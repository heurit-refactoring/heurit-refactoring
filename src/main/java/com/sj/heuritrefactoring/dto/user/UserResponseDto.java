package com.sj.heuritrefactoring.dto.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private Long userId;
    private String username;
    private String email;
    private String imgUrl;
}
