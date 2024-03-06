package cloud.robinzon.backend.db.vpn.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class VpnUserEntityListener {

    @Autowired
    private VpnUserEntityRepository vpnUserEntityRepository;

    @Autowired
    private VpnUserHistoryRepository vpnUserHistoryRepository;

    @PrePersist
    public void prePersist(VpnUserEntity vpnUserEntity) {
    }

    @PreUpdate
    public void preUpdate(VpnUserEntity vpnUserEntity) {
    }

}
