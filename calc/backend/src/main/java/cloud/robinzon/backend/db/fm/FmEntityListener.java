package cloud.robinzon.backend.db.fm;

import org.springframework.stereotype.Component;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class FmEntityListener {

    // @Autowired
    // private FmEntityRepository fmEntityRepository;

    // @Autowired
    // private FmHistoryRepository fmHistoryRepository;

    // @Autowired
    // private FmRentRepository fmRentRepository;

    @PrePersist
    public void prePersist(FmEntity fmEntity) {
    }

    @PreUpdate
    public void preUpdate(FmEntity fmEntity) {
    }

}
