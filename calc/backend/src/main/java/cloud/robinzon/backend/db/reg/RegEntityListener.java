package cloud.robinzon.backend.db.reg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cloud.robinzon.backend.db.vm.VmEntity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class RegEntityListener {

    @Autowired
    private RegEntityRepository regEntityRepository;

    @Autowired
    private RegHistoryRepository regHistoryRepository;

    @PrePersist
    private void prePersist(VmEntity vmEntity) {
    }

    @PreUpdate
    private void preUpdate(VmEntity vmEntity) {
    }

}
