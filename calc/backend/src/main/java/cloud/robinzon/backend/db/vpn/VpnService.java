package cloud.robinzon.backend.db.vpn;

import org.springframework.stereotype.Service;

import cloud.robinzon.backend.db.vpn.server.VpnServerEntityRepository;
import cloud.robinzon.backend.db.vpn.user.VpnUserEntityRepository;

@Service
public class VpnService {

    private VpnServerEntityRepository vpnServerEntityRepository;
    private VpnUserEntityRepository vpnUserEntityRepository;

    public VpnService(
            VpnServerEntityRepository vpnServerEntityRepository,
            VpnUserEntityRepository vpnUserEntityRepository) {
        this.vpnServerEntityRepository = vpnServerEntityRepository;
        this.vpnUserEntityRepository = vpnUserEntityRepository;
    }

}
