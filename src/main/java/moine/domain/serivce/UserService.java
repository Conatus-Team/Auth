package moine.domain.serivce;

import lombok.RequiredArgsConstructor;
import moine.domain.dto.SimpleDto;
import moine.domain.entity.User;
import moine.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    @Autowired
    private UserRepository userRepository;

    // validation
    public boolean validationUser(Long userId) {
        // 존재하는 userId
        if(userRepository.existsByUserId(userId)){
            return false;
        }

        return true;
    }

    // 회원가입
    public SimpleDto postSignUp(String email, String password, String userName, String userNickname) {

        if(!userRepository.existsByEmail(email)){
            // create
            User newUser = new User();

            newUser.setEmail(email);
            newUser.setPassword(password);
            newUser.setUserName(userName);
            newUser.setUserName(userNickname);


            User result = userRepository.save(newUser);

            return getSimpleUser(result);
        }
        else{
            // 이미 존재하는 유저
            return null;
        }

    }

    // 모든 사용자 가져오기
    public List<User> getUsers () {
        List<User> list = userRepository.findAll();

        return list;
    }

    // 리턴 형태
    public SimpleDto getSimpleUser(User user) {
        SimpleDto simpleDto = new SimpleDto();
        simpleDto.setUserId(user.getUserId());
        simpleDto.setEmail(user.getEmail());
        simpleDto.setUserName(user.getUserName());
        simpleDto.setUserNickname(user.getUserNickname());

        return simpleDto;
    }

}
