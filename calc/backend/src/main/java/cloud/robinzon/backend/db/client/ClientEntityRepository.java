package cloud.robinzon.backend.db.client;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientEntityRepository extends JpaRepository<ClientEntity, Long> {

    @Query("SELECT COUNT(c) > 0 FROM ClientEntity c WHERE c.inn = :inn AND c.deleted = false")
    boolean checkUniqueInn(String inn);

    @Query("SELECT COUNT(c) > 0 FROM ClientEntity c WHERE c.contractNumber = :contractNumber AND c.deleted = false")
    boolean checkUniqueContractNumber(Double contractNumber);

}
