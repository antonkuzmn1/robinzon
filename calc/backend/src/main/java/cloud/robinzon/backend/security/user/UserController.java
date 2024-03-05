package cloud.robinzon.backend.security.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("security/user/")
public class UserController {

    private UserService userService;

    public UserController(
            UserService userService) {
        this.userService = userService;
    }
}
