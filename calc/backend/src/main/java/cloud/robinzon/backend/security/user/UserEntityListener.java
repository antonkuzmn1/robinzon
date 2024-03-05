// package cloud.robinzon.backend.security.user;

// import org.springframework.stereotype.Component;

// import jakarta.persistence.PrePersist;
// import jakarta.persistence.PreUpdate;

// @Component
// public class UserEntityListener {

//     private UserEntityRepository userEntityRepository;
//     private UserHistoryRepository userHistoryRepository;

//     public UserEntityListener(
//             UserEntityRepository userEntityRepository,
//             UserHistoryRepository userHistoryRepository) {
//         this.userEntityRepository = userEntityRepository;
//         this.userHistoryRepository = userHistoryRepository;
//     }

//     @PrePersist
//     public void prePersist(UserEntity userEntity) {
//     }

//     @PreUpdate
//     public void preUpdate(UserEntity userEntity) {
//     }

// }
