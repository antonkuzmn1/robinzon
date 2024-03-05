package cloud.robinzon.backend.db.vpn;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("db/vpn/")
public class VpnController {

    private VpnService vpnService;

    public VpnController(
            VpnService vpnService) {
        this.vpnService = vpnService;
    }
}
