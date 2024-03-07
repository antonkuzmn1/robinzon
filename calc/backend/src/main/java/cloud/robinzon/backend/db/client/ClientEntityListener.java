package cloud.robinzon.backend.db.client;

import org.springframework.stereotype.Component;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

@Component
public class ClientEntityListener {

    // @Autowired
    // private ClientEntityRepository clientEntityRepository;

    // @Autowired
    // private ClientHistoryRepository clientHistoryRepository;

    @PrePersist
    public void prePersist(ClientEntity entity) {
    }

    @PostPersist
    public void postPersist(ClientEntity entity) {
    }

    @PreUpdate
    public void preUpdate(ClientEntity entity) {
    }

    @PostUpdate
    public void postUpdate(ClientEntity entity) {
    }

    @PreRemove
    public void preRemove(ClientEntity entity) {
    }

    @PostRemove
    public void postRemove(ClientEntity entity) {
    }

}
