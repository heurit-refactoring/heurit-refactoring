package com.sj.heuritrefactoring.dto.user;

import com.sj.heuritrefactoring.domain.user.SocialType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoDto {
    private String username;
    private String email;
    private String socialId;
    private String imgURL;
    private String refreshToken;
    private String deviceToken;
    private SocialType socialType;

    public UserInfoDto(String username, String email, String socialId, SocialType socialType, String imgURL) {
        this.username = username;
        this.email = email;
        this.socialId = socialId;
        this.socialType = socialType;
        this.imgURL = imgURL;
    }

    public User toEntity(){
        User user = new User(this.username, this.email, this.socialId, this.imgURL, this.refreshToken, this.deviceToken, this.socialType);
        return user;
    }
}
