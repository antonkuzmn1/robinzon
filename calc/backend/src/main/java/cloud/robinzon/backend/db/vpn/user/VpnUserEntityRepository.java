package cloud.robinzon.backend.db.vpn.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VpnUserEntityRepository extends JpaRepository<VpnUserEntity, Long> {
}
