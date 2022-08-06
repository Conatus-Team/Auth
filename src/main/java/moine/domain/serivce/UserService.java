package moine.domain.serivce;

import lombok.RequiredArgsConstructor;
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

    // user 추가
    public User postSignUp(Long userId, String userName, String password) {

        if(validationUser(userId)){
            // create
            User newUser = new User();

            newUser.setUserId(userId);
            newUser.setUserName(userName);
            newUser.setPassword(password);

            userRepository.save(newUser);

            return newUser;
        }
        else{

            return null;
        }

    }

    // 모든 사용자 가져오기
    public List<User> getUsers () {
        List<User> list = userRepository.findAll();

        return list;
    }

}
