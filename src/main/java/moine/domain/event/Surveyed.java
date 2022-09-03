package moine.domain.event;

import moine.infra.AbstractEvent;

import java.util.Date;

public class Surveyed extends AbstractEvent {
    private Long id;
    private Long userId;
    private Boolean gender;
    private Date birth;
    private String location;

    public Surveyed(){
        super();
    }
}

