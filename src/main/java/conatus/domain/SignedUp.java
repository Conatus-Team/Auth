package conatus.domain;

import conatus.domain.*;
import conatus.infra.AbstractEvent;
import java.util.Date;
import lombok.Data;

@Data
public class SignedUp extends AbstractEvent {

    private Long id;
    private Long userId;
    private Boolean gender;
    private Date birth;
    private String location;

    public SignedUp(AuthUserInfo aggregate) {
        super(aggregate);
    }

    public SignedUp() {
        super();
    }
    // keep

}
