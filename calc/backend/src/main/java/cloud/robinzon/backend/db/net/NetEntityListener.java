package cloud.robinzon.backend.db.net;

import org.springframework.stereotype.Component;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

@Component
public class NetEntityListener {

    // @Autowired
    // private NetEntityRepository netEntityRepository;

    // @Autowired
    // private NetHistoryRepository netHistoryRepository;

    // @Autowired
    // private NetRentRepository netRentRepository;

    @PrePersist
    private void prePersist(NetEntity entity) {
    }

    @PostPersist
    private void postPersist(NetEntity entity) {
    }

    @PreUpdate
    private void preUpdate(NetEntity entity) {
    }

    @PostUpdate
    private void postUpdate(NetEntity entity) {
    }

    @PreRemove
    private void preRemove(NetEntity entity) {
    }

    @PostRemove
    private void postRemove(NetEntity entity) {
    }

}
