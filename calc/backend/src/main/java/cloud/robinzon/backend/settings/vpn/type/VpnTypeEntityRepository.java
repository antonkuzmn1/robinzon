package cloud.robinzon.backend.settings.vpn.type;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VpnTypeEntityRepository extends JpaRepository<VpnTypeEntity, Long> {
}
