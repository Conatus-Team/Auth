package moine.domain.entity;

import lombok.Getter;
import lombok.Setter;
import moine.AuthApplication;
import javax.persistence.*;

import moine.domain.event.SignedUp;
import moine.domain.repository.UserRepository;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@Entity
@Table(name="user")
public class User  {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Long userId;

    private String userName;

    private String userNickname;

    private String email;

    private String password;

    @PostPersist
    public void onPostPersist(){
        SignedUp signedUp = new SignedUp();
        BeanUtils.copyProperties(this, signedUp);
        signedUp.publishAfterCommit();

    }

    public static UserRepository repository(){
        UserRepository authUserInfoRepository = AuthApplication.applicationContext.getBean(UserRepository.class);
        return authUserInfoRepository;
    }




}
