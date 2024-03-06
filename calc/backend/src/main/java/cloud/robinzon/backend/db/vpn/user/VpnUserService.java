package cloud.robinzon.backend.db.vpn.user;

import org.springframework.stereotype.Service;

@Service
public class VpnUserService {

    private VpnUserEntityRepository vpnUserEntityRepository;

    public VpnUserService(
            VpnUserEntityRepository vpnUserEntityRepository) {
        this.vpnUserEntityRepository = vpnUserEntityRepository;
    }

}
