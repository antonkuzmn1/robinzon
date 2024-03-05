// package cloud.robinzon.backend.settings.vm.price;

// import org.springframework.stereotype.Component;

// import jakarta.persistence.PrePersist;
// import jakarta.persistence.PreUpdate;

// @Component
// public class VmPriceListener {

//     private VmPriceEntityRepository vmPriceEntityRepository;
//     private VmPriceHistoryRepository vmPriceHistoryRepository;

//     public VmPriceListener(
//             VmPriceEntityRepository vmPriceEntityRepository,
//             VmPriceHistoryRepository vmPriceHistoryRepository) {
//         this.vmPriceEntityRepository = vmPriceEntityRepository;
//         this.vmPriceHistoryRepository = vmPriceHistoryRepository;
//     }

//     @PrePersist
//     public void prePersist(VmPriceEntity vmPriceEntity) {
//     }

//     @PreUpdate
//     public void preUpdate(VmPriceEntity vmPriceEntity) {
//     }

// }
