package cloud.robinzon.backend.db.client;

import java.util.Date;

import org.springframework.stereotype.Service;

import cloud.robinzon.backend.ResponseForm;

@Service
public class ClientEntityManager {

    private ClientEntityRepository entityRepository;
    private ClientHistoryRepository historyRepository;
    private ClientPaymentRepository paymentRepository;

    public ClientEntityManager(
            ClientEntityRepository entityRepository,
            ClientHistoryRepository historyRepository,
            ClientPaymentRepository paymentRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
        this.paymentRepository = paymentRepository;
    }

    private ResponseForm responseForm = new ResponseForm("ClientEntityManager");

    public ResponseForm insert(
            String name,
            String inn,
            int discount,
            Double contractNumber,
            Date contractDate,
            String title,
            int balance,
            String description) {
        responseForm.function("insert");

        if (name == null)
            return responseForm.error("Name cannot be null");
        if (name.length() > 100)
            return responseForm.error("Name cannot contain more than 100 characters");
        if (entityRepository.checkUniqueInn(inn))
            return responseForm.error("Entity with inn " + inn + " already exists");
        if (name.length() > 12)
            return responseForm.error("Name cannot contain more than 100 characters");
        if (discount < 0)
            return responseForm.error("Discount cannot be less than 0 percents");
        if (discount > 100)
            return responseForm.error("Discount cannot be more than 100 percents");
        if (contractNumber < 1)
            return responseForm.error("Contract number cannot be less than 1");
        if (entityRepository.checkUniqueContractNumber(contractNumber))
            return responseForm.error("Entity with contract number " + contractNumber.toString() + " already exists");
        if (title == null)
            title = "";
        if (title.length() > 50)
            return responseForm.error("Title cannot contain more than 50 characters");
        if (balance < 0)
            return responseForm.error("Balance cannot be less than 0");
        if (description == null)
            description = "";
        if (description.length() > 255)
            return responseForm.error("Description cannot contain more than 255 characters");

        ClientEntity entity = new ClientEntity(
                name,
                inn,
                discount,
                contractNumber,
                contractDate,
                title,
                balance,
                description);
        entityRepository.save(entity);

        ClientHistory history = new ClientHistory(
                entity,
                name,
                inn,
                discount,
                contractNumber,
                contractDate,
                title,
                description,
                null); // spring security system required
        historyRepository.save(history);

        ClientPayment payment = new ClientPayment(
                entity,
                balance);
        paymentRepository.save(payment);

        return responseForm.success("Inserted new client: " + entity.getName());
    }

    public ResponseForm update(
            Long id,
            String name,
            String inn,
            int discount,
            Double contractNumber,
            Date contractDate,
            String title,
            String description) {
        responseForm.function("update");

        if (name == null)
            return responseForm.error("Name cannot be null");
        if (name.length() > 100)
            return responseForm.error("Name cannot contain more than 100 characters");
        if (entityRepository.checkUniqueInn(inn))
            return responseForm.error("Entity with inn " + inn + " already exists");
        if (name.length() > 12)
            return responseForm.error("Name cannot contain more than 100 characters");
        if (discount < 0)
            return responseForm.error("Discount cannot be less than 0 percents");
        if (discount > 100)
            return responseForm.error("Discount cannot be more than 100 percents");
        if (contractNumber < 1)
            return responseForm.error("Contract number cannot be less than 1");
        if (entityRepository.checkUniqueContractNumber(contractNumber))
            return responseForm.error("Entity with contract number " + contractNumber.toString() + " already exists");
        if (title == null)
            title = "";
        if (title.length() > 50)
            return responseForm.error("Title cannot contain more than 50 characters");
        if (description == null)
            description = "";
        if (description.length() > 255)
            return responseForm.error("Description cannot contain more than 255 characters");

        ClientEntity entity = entityRepository.findById(id).orElse(null);

        if (entity == null)
            return responseForm.error("Client with id " + id + " not found");

        if (entity.getName().equals(name)
                && entity.getInn().equals(inn)
                && entity.getDiscount() == discount
                && entity.getContractNumber().equals(contractNumber)
                && entity.getContractDate().equals(contractDate)
                && entity.getTitle().equals(title)
                && entity.getDescription().equals(description))
            return responseForm.error("All params of " + name + " is equal");

        entity.setName(name);
        entity.setInn(inn);
        entity.setDiscount(discount);
        entity.setContractNumber(contractNumber);
        entity.setContractDate(contractDate);
        entity.setTitle(title);
        entity.setDescription(description);
        entityRepository.save(entity);

        ClientHistory history = new ClientHistory(
                entity,
                name,
                inn,
                discount,
                contractNumber,
                contractDate,
                title,
                description,
                null); // spring security system required
        historyRepository.save(history);

        return responseForm.success("Updated client: " + entity.getName());
    }

    public ResponseForm delete(Long id) {
        responseForm.function("delete");

        if (id == null)
            return responseForm.error("Id cannot be null");
        if (id < 1)
            return responseForm.error("Id cannot be less than 1");
        
        ClientEntity entity = entityRepository.findById(id).orElse(null);

        if (entity == null)
            return responseForm.error("Client with id " + id + " not found");

        entity.setDeleted(true);
        entityRepository.save(entity);

        return responseForm.success("Deleted client: " + entity.getName());
    }

}
