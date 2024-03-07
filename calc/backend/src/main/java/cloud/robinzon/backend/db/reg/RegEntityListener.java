package cloud.robinzon.backend.db.reg;

import org.springframework.stereotype.Component;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

@Component
public class RegEntityListener {

    // @Autowired
    // private RegEntityRepository regEntityRepository;

    // @Autowired
    // private RegHistoryRepository regHistoryRepository;

    @PrePersist
    private void prePersist(RegEntity entity) {
    }

    @PostPersist
    private void postPersist(RegEntity entity) {
    }

    @PreUpdate
    private void preUpdate(RegEntity entity) {
    }

    @PostUpdate
    private void postUpdate(RegEntity entity) {
    }

    @PreRemove
    private void preRemove(RegEntity entity) {
    }

    @PostRemove
    private void postRemove(RegEntity entity) {
    }

}
