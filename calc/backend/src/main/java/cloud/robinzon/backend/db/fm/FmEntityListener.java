// package cloud.robinzon.backend.db.fm;

// import org.springframework.stereotype.Component;

// import jakarta.persistence.PrePersist;
// import jakarta.persistence.PreUpdate;

// @Component
// public class FmEntityListener {

//     private FmEntityRepository fmEntityRepository;
//     private FmHistoryRepository fmHistoryRepository;
//     private FmRentRepository fmRentRepository;

//     public FmEntityListener(
//             FmEntityRepository fmEntityRepository,
//             FmHistoryRepository fmHistoryRepository,
//             FmRentRepository fmRentRepository) {
//         this.fmEntityRepository = fmEntityRepository;
//         this.fmHistoryRepository = fmHistoryRepository;
//         this.fmRentRepository = fmRentRepository;
//     }

//     @PrePersist
//     public void prePersist(FmEntity fmEntity) {
//     }

//     @PreUpdate
//     public void preUpdate(FmEntity fmEntity) {
//     }

// }
