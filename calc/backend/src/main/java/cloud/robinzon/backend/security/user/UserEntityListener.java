package cloud.robinzon.backend.security.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class UserEntityListener {

    @Autowired
    private UserEntityRepository userEntityRepository;
    
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @PrePersist
    public void prePersist(UserEntity userEntity) {
    }

    @PreUpdate
    public void preUpdate(UserEntity userEntity) {
    }

}
