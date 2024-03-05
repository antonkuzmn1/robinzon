package cloud.robinzon.backend.db.fm;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FmEntityRepository extends JpaRepository<FmEntity, Long> {

    List<FmEntity> findByVm(Boolean vm);

}
