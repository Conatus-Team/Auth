package conatus.infra;

import conatus.domain.*;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/authUserInfos")
@Transactional
public class AuthUserInfoController {

    @Autowired
    AuthUserInfoRepository authUserInfoRepository;
    // keep
}
