package cloud.robinzon.backend.db.vpn.server;

import org.springframework.stereotype.Component;

import jakarta.persistence.PostPersist;
import jakarta.persistence.PostRemove;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;

@Component
public class VpnServerEntityListener {

    // @Autowired
    // private VpnServerEntityRepository vpnServerEntityRepository;

    // @Autowired
    // private VpnServerHistoryRepository vpnServerHistoryRepository;

    @PrePersist
    public void prePersist(VpnServerEntity vpnServerEntity) {
    }

    @PostPersist
    public void postPersist(VpnServerEntity vpnServerEntity) {
    }

    @PreUpdate
    public void preUpdate(VpnServerEntity vpnServerEntity) {
    }

    @PostUpdate
    public void postUpdate(VpnServerEntity vpnServerEntity) {
    }

    @PreRemove
    public void preRemove(VpnServerEntity vpnServerEntity) {
    }

    @PostRemove
    public void postRemove(VpnServerEntity vpnServerEntity) {
    }

}
