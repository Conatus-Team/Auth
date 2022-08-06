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

    @Column(name="user_id", nullable = false, length = 100)
    private Long userId;

    @Column(name="user_name", nullable = false, length = 100)
    private String userName;

    @Column(name="user_nickname",nullable = true, length = 100)
    private String userNickname;

    @Column(name="email",nullable = true, length = 100)
    private String email;

    @Column(name="password",nullable = false, length = 100)
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
