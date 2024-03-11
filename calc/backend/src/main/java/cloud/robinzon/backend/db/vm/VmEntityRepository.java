package cloud.robinzon.backend.db.vm;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VmEntityRepository extends JpaRepository<VmEntity, String> {

    @Query("UPDATE VmEntity v SET v.deleted = true WHERE v.deleted = false")
    void markAsDeletedAll();

}
