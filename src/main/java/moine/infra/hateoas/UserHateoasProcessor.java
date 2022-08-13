package moine.infra.hateoas;

import moine.domain.entity.User;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.EntityModel;

@Component
public class UserHateoasProcessor implements RepresentationModelProcessor<EntityModel<User>>  {

    @Override
    public EntityModel<User> process(EntityModel<User> model) {
        
        return model;
    }
    
}

