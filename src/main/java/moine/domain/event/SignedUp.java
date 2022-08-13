package moine.domain.event;

import lombok.Getter;
import lombok.Setter;
import moine.infra.AbstractEvent;
import java.util.Date; 

@Getter
@Setter
public class SignedUp extends AbstractEvent {


    private Long userId;
    private String email;
    private String password;
    private String userName;
    private String userNickname;

    public SignedUp(){
        super();
    }


}
