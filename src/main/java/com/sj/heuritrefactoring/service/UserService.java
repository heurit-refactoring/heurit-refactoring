package com.sj.heuritrefactoring.service;

import com.sj.heuritrefactoring.domain.user.SocialType;
import com.sj.heuritrefactoring.domain.user.UserRepository;
import com.sj.heuritrefactoring.dto.user.User;
import com.sj.heuritrefactoring.dto.user.UserInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void insertOrUpdateUser(UserInfoDto userInfoDto) {
        String socialId = userInfoDto.getSocialId();
        SocialType socialType = userInfoDto.getSocialType();
        //처음 로그인 하는 유저면 DB에 insert
        if(Boolean.FALSE.equals(findUserBySocialData(socialId, socialType).isPresent())){
            User user = userInfoDto.toEntity(); //기본 Role = ROLE.USER
            userRepository.save(user);
        }else{ //이미 로그인 했던 유저라면 DB update
            updateUserBySocialData(userInfoDto);
        }
    }


    public Optional<User> findUserBySocialData(String socialId, SocialType socialType){
        Optional<User> user = userRepository.findBySocialIdAndSocialType(socialId, socialType);
        return user;
    }

    public void updateUserBySocialData(UserInfoDto userInfo){
        userRepository.updateUserBySocialIdAndSocialType(userInfo.getUsername(), userInfo.getEmail(), userInfo.getImgURL(), userInfo.getRefreshToken() ,userInfo.getSocialId(), userInfo.getSocialType());
    }
}
