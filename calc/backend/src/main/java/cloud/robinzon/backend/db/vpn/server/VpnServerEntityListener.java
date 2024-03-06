package cloud.robinzon.backend.db.vpn.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class VpnServerEntityListener {

    @Autowired
    private VpnServerEntityRepository vpnServerEntityRepository;

    @Autowired
    private VpnServerHistoryRepository vpnServerHistoryRepository;

    @PrePersist
    public void prePersist(VpnServerEntity vpnServerEntity) {
    }

    @PreUpdate
    public void preUpdate(VpnServerEntity vpnServerEntity) {
    }

}
