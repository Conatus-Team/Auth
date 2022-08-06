package moine.infra.controller;

import lombok.RequiredArgsConstructor;
import moine.domain.repository.UserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

@RestController

@RequiredArgsConstructor
@RequestMapping(value="/auth")
public class AuthUserInfoController {
    @Autowired
    UserRepository authUserInfoRepository;

//    // 회원가입
//    @PostMapping("/signUp")
//    public User postUser(@RequestBody SignUpDto signUpDto) {
//        System.out.println("signUpDto = " + signUpDto);
//        User newUser = userService.postUser(signUpDto.getUserName(), signUpDto.getUserNickname());
//
//        return newUser;
//    }
}
