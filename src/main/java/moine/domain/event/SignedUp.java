package moine.domain.event;

import lombok.Getter;
import lombok.Setter;
import moine.infra.AbstractEvent;
import java.util.Date; 

@Getter
@Setter
public class SignedUp extends AbstractEvent {

    private Long id;
    private Long userId;
    private Boolean gender;
    private Date birth;
    private String location;

    public SignedUp(){
        super();
    }


}
