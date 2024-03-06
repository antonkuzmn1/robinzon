package cloud.robinzon.backend.db.net;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cloud.robinzon.backend.db.vm.VmEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class NetEntityListener {

    @Autowired
    private NetEntityRepository netEntityRepository;

    @Autowired
    private NetHistoryRepository netHistoryRepository;

    @Autowired
    private NetRentRepository netRentRepository;

    @PrePersist
    private void prePersist(VmEntity vmEntity) {
    }

    @PreUpdate
    private void preUpdate(VmEntity vmEntity) {
    }

}
