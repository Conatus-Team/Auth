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
    @Column(name="user_id")
    private Long userId;

    @Column(name="user_name", nullable = false, length = 100)
    private String userName;

    @Column(name="user_nickname",nullable = true, length = 100)
    private String userNickname;

    @Column(name="email",nullable = false, length = 100, unique = true)
    private String email;

    @Column(name="password",nullable = false, length = 100)
    private String password;


}
