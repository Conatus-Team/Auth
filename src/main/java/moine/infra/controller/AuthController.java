package moine.infra.controller;

import lombok.RequiredArgsConstructor;
import moine.domain.dto.SignUpDto;
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
    // .
    @PostMapping("/signUp")
    public User postUser(@RequestBody SignUpDto signUpDto) {
        System.out.println("signUpDto = " + signUpDto);
        User newUser = userService.postSignUp(
                signUpDto.getUserId(),
                signUpDto.getUserName(),
                signUpDto.getPassword()
        );

        return newUser;
    }


}
