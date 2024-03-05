// package cloud.robinzon.backend.db.client;

// import org.springframework.stereotype.Component;

// import jakarta.persistence.PrePersist;
// import jakarta.persistence.PreUpdate;

// @Component
// public class ClientEntityListener {

//     private ClientEntityRepository clientEntityRepository;
//     private ClientHistoryRepository clientHistoryRepository;

//     public ClientEntityListener(
//             ClientEntityRepository clientEntityRepository,
//             ClientHistoryRepository clientHistoryRepository) {
//         this.clientEntityRepository = clientEntityRepository;
//         this.clientHistoryRepository = clientHistoryRepository;
//     }

//     @PrePersist
//     public void prePersist(ClientEntity clientEntity) {
//     }

//     @PreUpdate
//     public void preUpdate(ClientEntity clientEntity) {
//     }

// }
