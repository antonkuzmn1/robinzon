package cloud.robinzon.backend.settings.vm.price;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class VmPriceListener {

    @Autowired
    private VmPriceEntityRepository vmPriceEntityRepository;

    @Autowired
    private VmPriceHistoryRepository vmPriceHistoryRepository;

    @PrePersist
    public void prePersist(VmPriceEntity vmPriceEntity) {
    }

    @PreUpdate
    public void preUpdate(VmPriceEntity vmPriceEntity) {
    }

}
