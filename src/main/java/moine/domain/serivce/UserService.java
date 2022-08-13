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
    public boolean existUser(Long userId) {
        // 존재하는 userId
        return userRepository.existsByUserId(userId);
    }

    // 모든 사용자 가져오기
    public List<User> getUsers () {
        List<User> list = userRepository.findAll();

        return list;
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
            // 이미 존재하는 이메일

        }
        return null;

    }

    // 로그인
    public SimpleDto getLoginUser(String email, String password) {
        if(userRepository.existsByEmail(email)){
            List<User> user = userRepository.findByEmailAndPassword(email, password);
            if(!user.isEmpty()){
                return getSimpleUser(user.get(0));
            }
            else{
                // 잘못된 비밀번호
            }
        }
        else{
           // 존재하지 않는 이메일
        }

        return null;
    }


    // 마이페이지
    public SimpleDto getMyPageUser(Long userId){
        if(existUser(userId)){
            User user = userRepository.findById(userId).get();
            return getSimpleUser(user);
        }
        else {
            // 존재하지 않는 userId
        }
        return null;

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
