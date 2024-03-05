package cloud.robinzon.backend.security.group;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupEntityRepository extends JpaRepository<GroupEntity, Long> {
}
