package cloud.robinzon.backend.security.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class GroupEntityListener {

    @Autowired
    private GroupEntityRepository groupEntityRepository;

    @Autowired
    private GroupHistoryRepository groupHistoryRepository;

    @PrePersist
    public void prePersist(GroupEntity groupEntity) {
    }

    @PreUpdate
    public void preUpdate(GroupEntity groupEntity) {
    }

}
