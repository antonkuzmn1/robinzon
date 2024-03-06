package cloud.robinzon.backend.settings;

import org.springframework.stereotype.Service;

import cloud.robinzon.backend.settings.vm.price.VmPriceEntityRepository;
import cloud.robinzon.backend.settings.vpn.type.VpnTypeEntityRepository;

@Service
public class SettingsService {

    private VmPriceEntityRepository vmPriceEntityRepository;
    private VpnTypeEntityRepository vpnTypeEntityRepository;

    public SettingsService(
            VmPriceEntityRepository vmPriceEntityRepository,
            VpnTypeEntityRepository vpnTypeEntityRepository) {
        this.vmPriceEntityRepository = vmPriceEntityRepository;
        this.vpnTypeEntityRepository = vpnTypeEntityRepository;
    }

}
