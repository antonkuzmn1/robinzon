package cloud.robinzon.backend.security.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserEntityRepository userEntityRepository;

    public UserService(
            UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

}
