package moine.domain.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import moine.domain.middle.PostMiddleService;
import moine.domain.middle.Url;
import moine.domain.event.SignedUp;
import moine.domain.repository.UserRepository;
import moine.domain.serivce.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@Entity
@Table(name="user")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="user_id")
    private Long userId;

    // soft delete
    @Column(name="is_deleted", nullable = false)
    private boolean isDeleted = Boolean.FALSE; // 디폴트 false

    @Column(name="user_name", nullable = false, length = 100)
    private String userName;

    @Column(name="user_nickname",nullable = true, length = 100)
    private String userNickname;

    @Column(name="email",nullable = false, length = 100, unique = true)
    private String email;

    @Column(name="password",nullable = false, length = 100)
    private String password;

    @Async
    @PostPersist
    public void onPostPersist() {

        try {
            // DB에 유저 create 후 kafka에 SignedUp 이벤트 발송
            SignedUp signedUp = new SignedUp();
            BeanUtils.copyProperties(this, signedUp);
            signedUp.publishAfterCommit();

            System.out.println("====================================");
            System.out.println("====================================");
            System.out.println("회원가입 이벤트 발송 성공!");
            System.out.println(signedUp);
            System.out.println("====================================");
            System.out.println("====================================");

            // Middle 서버로 http request
            PostMiddleService postMiddleService = new PostMiddleService();
            postMiddleService.sendTo(Url.MIDDLE.getUrl() + "/SignedUp", signedUp);
        }
        catch (Exception e) {
            // 만들어진 유저 다시 삭제

        }

    }



}
