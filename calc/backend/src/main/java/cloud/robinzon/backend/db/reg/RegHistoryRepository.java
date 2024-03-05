package cloud.robinzon.backend.db.reg;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegHistoryRepository extends JpaRepository<RegHistory, RegHistoryKey> {
}
