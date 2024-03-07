package cloud.robinzon.backend.db.fm;

import org.springframework.stereotype.Component;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
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
    public void prePersist(FmEntity entity) {
    }

    @PostPersist
    public void postPersist(FmEntity entity) {
    }

    @PreUpdate
    public void preUpdate(FmEntity entity) {
    }

    @PostUpdate
    public void postUpdate(FmEntity entity) {
    }

    @PreRemove
    public void preRemove(FmEntity entity) {
    }

    @PostRemove
    public void postRemove(FmEntity entity) {
    }

}
