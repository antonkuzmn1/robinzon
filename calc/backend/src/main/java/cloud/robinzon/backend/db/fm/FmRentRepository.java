package cloud.robinzon.backend.db.fm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FmRentRepository extends JpaRepository<FmRent, FmRentKey> {
}
