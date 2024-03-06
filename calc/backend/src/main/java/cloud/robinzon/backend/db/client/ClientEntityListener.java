package cloud.robinzon.backend.db.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class ClientEntityListener {

    @Autowired
    private ClientEntityRepository clientEntityRepository;

    @Autowired
    private ClientHistoryRepository clientHistoryRepository;

    @PrePersist
    public void prePersist(ClientEntity clientEntity) {
    }

    @PreUpdate
    public void preUpdate(ClientEntity clientEntity) {
    }

}
