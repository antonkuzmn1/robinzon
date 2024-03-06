package cloud.robinzon.backend.settings.vpn.type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;

@Component
public class VpnTypeListener {

    @Autowired
    private VpnTypeEntityRepository vpnTypeEntityRepository;

    @Autowired
    private VpnTypeHistoryRepository vpnTypeHistoryRepository;

    @PrePersist
    public void prePersist(VpnTypeEntity vpnTypeEntity) {
    }

    @PreUpdate
    public void preUpdate(VpnTypeEntity vpnTypeEntity) {
    }

}
