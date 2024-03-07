package cloud.robinzon.backend.db.client;

import org.springframework.stereotype.Component;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class ClientEntityListener {

    // @Autowired
    // private ClientEntityRepository clientEntityRepository;

    // @Autowired
    // private ClientHistoryRepository clientHistoryRepository;

    @PrePersist
    public void prePersist(ClientEntity clientEntity) {
    }

    @PostPersist
    public void postPersist(ClientEntity clientEntity) {
    }

    @PreUpdate
    public void preUpdate(ClientEntity clientEntity) {
    }

    @PostUpdate
    public void postUpdate(ClientEntity clientEntity) {
    }

}
