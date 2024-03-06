package cloud.robinzon.backend.db.vpn.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("db/vpn/user/")
public class VpnUserController {

    private VpnUserService vpnUserService;

    public VpnUserController(
            VpnUserService vpnUserService) {
        this.vpnUserService = vpnUserService;
    }

}
