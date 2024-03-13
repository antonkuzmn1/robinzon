package cloud.robinzon.backend.db.vpn.server;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VpnServerHistoryRepository
        extends JpaRepository<VpnServerHistory, VpnServerHistoryKey> {
}
