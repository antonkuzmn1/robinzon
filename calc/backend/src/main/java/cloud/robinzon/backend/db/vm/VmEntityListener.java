package cloud.robinzon.backend.db.vm;

import org.springframework.stereotype.Component;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

@Component
public class VmEntityListener {

    // @Autowired
    // private VmEntityRepository vmEntityRepository;

    // @Autowired
    // private VmHistoryRepository vmHistoryRepository;

    // @Autowired
    // private VmRentRepository vmRentRepository;

    @PrePersist
    private void prePersist(VmEntity entity) {
    }

    @PostPersist
    private void postPersist(VmEntity entity) {
    }

    @PreUpdate
    private void preUpdate(VmEntity entity) {
    }

    @PostUpdate
    private void postUpdate(VmEntity entity) {
    }

    @PreRemove
    private void preRemove(VmEntity entity) {
    }

    @PostRemove
    private void postRemove(VmEntity entity) {
    }

}
