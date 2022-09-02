package moine.infra.controller;

import lombok.RequiredArgsConstructor;
import moine.domain.dto.LoginDto;
import moine.domain.dto.SignUpDto;
import moine.domain.dto.SimpleDto;
import moine.domain.entity.User;
import moine.domain.repository.UserRepository;
import moine.domain.serivce.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value="/auth")
public class AuthController {
    @Autowired
    UserRepository authUserInfoRepository;

    private final UserService userService;

    // 전체 사용자 목록
    @GetMapping("/all")
    public List<User> getAllUsers(){
        List<User> users = userService.getUsers();

        return users;
    }


    // 회원가입
    @PostMapping("/signup")
    public SimpleDto postUser(@RequestBody SignUpDto signUpDto) {
        System.out.println("signUpDto = " + signUpDto);
        SimpleDto newUser = userService.postSignUp(
                signUpDto.getEmail(),
                signUpDto.getPassword(),
                signUpDto.getUserName(),
                signUpDto.getUserNickname()
        );

        return newUser;
    }

    // 로그인
    // 브라우저 session 스토리지에 user 전달
    @PostMapping("/login")
    public SimpleDto postLoginUser(@RequestBody LoginDto loginDto){
        SimpleDto user = userService.getLoginUser(
                loginDto.getEmail(),
                loginDto.getPassword()
        );

        return user;
    }

    // 마이페이지
    // header에 값으로 key = Authorization, value = 1 로 테스트
    // Authorization는 userId를 의미
    @GetMapping("/mypage")
    public SimpleDto getMypage(@RequestHeader Long Authorization){
        SimpleDto user = userService.getMyPageUser(Authorization);
        return user;
    }


}
