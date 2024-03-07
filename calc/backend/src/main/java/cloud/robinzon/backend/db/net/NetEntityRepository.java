package cloud.robinzon.backend.db.net;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NetEntityRepository extends JpaRepository<NetEntity, Long> {

    @Query("SELECT COUNT(n) > 0 FROM NetEntity n WHERE n.subnet = :subnet AND n.deleted = false")
    boolean checkUniqueSubnet(String subnet);

}
