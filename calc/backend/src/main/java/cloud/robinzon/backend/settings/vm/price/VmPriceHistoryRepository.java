package cloud.robinzon.backend.settings.vm.price;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VmPriceHistoryRepository extends JpaRepository<VmPriceHistory, VmPriceHistoryKey> {
}
