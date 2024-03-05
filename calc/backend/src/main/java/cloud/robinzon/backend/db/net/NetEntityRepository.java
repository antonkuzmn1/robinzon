package cloud.robinzon.backend.db.net;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetEntityRepository extends JpaRepository<NetEntity, Long> {
}
