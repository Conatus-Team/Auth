package moine.domain.entity;

import lombok.Getter;
import lombok.Setter;
import moine.AuthApplication;
import javax.persistence.*;

import moine.domain.event.SignedUp;
import moine.domain.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

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

    @PostPersist
    public void onPostPersist() {
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
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

        HttpEntity<SignedUp> entity = new HttpEntity<>(signedUp, headers);

        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange(
                "http://localhost:8080/middle/SignedUp", //{요청할 서버 주소}
                HttpMethod.POST, //{요청할 방식}
                entity, // {요청할 때 보낼 데이터}
                String.class
        );

    }



}
