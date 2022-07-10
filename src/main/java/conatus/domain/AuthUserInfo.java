package conatus.domain;

import conatus.AuthApplication;
import conatus.domain.SignedUp;
import java.util.Date;
import java.util.List;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "AuthUserInfo_table")
@Data
public class AuthUserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userId;

    private String nickname;

    private String email;

    private String password;

    @PostPersist
    public void onPostPersist() {
        SignedUp signedUp = new SignedUp(this);
        signedUp.publishAfterCommit();
    }

    public static AuthUserInfoRepository repository() {
        AuthUserInfoRepository authUserInfoRepository = AuthApplication.applicationContext.getBean(
            AuthUserInfoRepository.class
        );
        return authUserInfoRepository;
    }
}
