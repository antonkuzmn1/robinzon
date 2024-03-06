package cloud.robinzon.backend.db.vm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class VmEntityListener {

    @Autowired
    private VmEntityRepository vmEntityRepository;

    @Autowired
    private VmHistoryRepository vmHistoryRepository;

    @Autowired
    private VmRentRepository vmRentRepository;

    @PrePersist
    private void prePersist(VmEntity vmEntity) {
    }

    @PreUpdate
    private void preUpdate(VmEntity vmEntity) {
    }

}
