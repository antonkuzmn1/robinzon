package cloud.robinzon.backend.db.net;

import org.springframework.stereotype.Service;

import cloud.robinzon.backend.ResponseForm;
import cloud.robinzon.backend.db.client.ClientEntity;
import cloud.robinzon.backend.db.client.ClientEntityRepository;

@Service
public class NetEntityManager {

    private NetEntityRepository entityRepository;
    private NetHistoryRepository historyRepository;
    private NetRentRepository rentRepository;
    private ClientEntityRepository clientEntityRepository;

    public NetEntityManager(
            NetEntityRepository entityRepository,
            NetHistoryRepository historyRepository,
            NetRentRepository rentRepository,
            ClientEntityRepository clientEntityRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
        this.rentRepository = rentRepository;
        this.clientEntityRepository = clientEntityRepository;
    }

    private ResponseForm responseForm = new ResponseForm("NetEntityManager");

    public ResponseForm insert(
            String domain,
            String subnet,
            String mask,
            String dns1,
            String dns2,
            String dns3,
            boolean cloud,
            String title,
            String description,
            ClientEntity clientEntity) {
        responseForm.function("insert");

        if (domain == null)
            domain = "";
        if (domain.length() > 50)
            return responseForm.error("Domain cannot contain more than 50 characters");
        if (subnet == null)
            return responseForm.error("Subnet cannot be null");
        if (entityRepository.checkUniqueSubnet(subnet))
            return responseForm.error("Net with subnet " + subnet + " already exists");
        if (subnet.length() > 15)
            return responseForm.error("Subnet cannot contain more than 15 characters");
        if (mask == null)
            return responseForm.error("Mask cannot be null");
        if (mask.length() > 15)
            return responseForm.error("Mask cannot contain more than 15 characters");
        if (dns1 == null)
            dns1 = "";
        if (dns1.length() > 15)
            return responseForm.error("DNS1 cannot contain more than 15 characters");
        if (dns2 == null)
            dns2 = "";
        if (dns2.length() > 15)
            return responseForm.error("DNS2 cannot contain more than 15 characters");
        if (dns3 == null)
            dns3 = "";
        if (dns3.length() > 15)
            return responseForm.error("DNS3 cannot contain more than 15 characters");
        if (title == null)
            title = "";
        if (title.length() > 50)
            return responseForm.error("Title cannot contain more than 50 characters");
        if (description == null)
            description = "";
        if (description.length() > 255)
            return responseForm.error("Description cannot contain more than 255 characters");
        if (clientEntityRepository.findById(clientEntity.getId()).orElse(null) == null)
            return responseForm.error("Client with ID " + clientEntity.getId() + " not found");

        NetEntity entity = new NetEntity(
                clientEntity,
                domain,
                subnet,
                mask,
                dns1,
                dns2,
                dns3,
                cloud,
                title,
                description);
        entityRepository.save(entity);

        NetHistory history = new NetHistory(
                entity,
                null, // spring security system required
                domain,
                subnet,
                mask,
                dns1,
                dns2,
                dns3,
                cloud,
                title,
                description,
                false);
        historyRepository.save(history);

        NetRent rent = new NetRent(
                entity,
                clientEntity,
                null); // spring security system required
        rentRepository.save(rent);

        return responseForm.success("Updated net: " + entity.getSubnet());
    }

    public ResponseForm update(
            Long id,
            String domain,
            String subnet,
            String mask,
            String dns1,
            String dns2,
            String dns3,
            boolean cloud,
            String title,
            String description) {
        responseForm.function("update");

        if (domain == null)
            domain = "";
        if (domain.length() > 50)
            return responseForm.error("Domain cannot contain more than 50 characters");
        if (subnet == null)
            return responseForm.error("Subnet cannot be null");
        if (entityRepository.checkUniqueSubnet(subnet))
            return responseForm.error("Net with subnet " + subnet + " already exists");
        if (subnet.length() > 15)
            return responseForm.error("Subnet cannot contain more than 15 characters");
        if (mask == null)
            return responseForm.error("Mask cannot be null");
        if (mask.length() > 15)
            return responseForm.error("Mask cannot contain more than 15 characters");
        if (dns1 == null)
            dns1 = "";
        if (dns1.length() > 15)
            return responseForm.error("DNS1 cannot contain more than 15 characters");
        if (dns2 == null)
            dns2 = "";
        if (dns2.length() > 15)
            return responseForm.error("DNS2 cannot contain more than 15 characters");
        if (dns3 == null)
            dns3 = "";
        if (dns3.length() > 15)
            return responseForm.error("DNS3 cannot contain more than 15 characters");
        if (title == null)
            title = "";
        if (title.length() > 50)
            return responseForm.error("Title cannot contain more than 50 characters");
        if (description == null)
            description = "";
        if (description.length() > 255)
            return responseForm.error("Description cannot contain more than 255 characters");

        NetEntity entity = entityRepository.findById(id).orElse(null);

        if (entity == null)
            return responseForm.error("Net with ID " + id + " not found");

        if (entity.getDomain().equals(domain)
                && entity.getSubnet().equals(subnet)
                && entity.getMask().equals(mask)
                && entity.getDns1().equals(dns1)
                && entity.getDns2().equals(dns2)
                && entity.getDns3().equals(dns3)
                && entity.isCloud() == cloud
                && entity.getTitle().equals(title)
                && entity.getDescription().equals(description))
            return responseForm.error("All params of " + entity.getSubnet() + " is equal");

        entity.setDomain(domain);
        entity.setSubnet(subnet);
        entity.setMask(mask);
        entity.setDns1(dns1);
        entity.setDns2(dns2);
        entity.setDns3(dns3);
        entity.setCloud(cloud);
        entity.setTitle(title);
        entity.setDescription(description);

        NetHistory history = new NetHistory(
                entity,
                null, // spring security system required
                domain,
                subnet,
                mask,
                dns1,
                dns2,
                dns3,
                cloud,
                title,
                description,
                false);
        historyRepository.save(history);

        return responseForm.success("Updated net: " + entity.getSubnet());
    }

    public ResponseForm delete(Long id) {
        responseForm.function("delete");

        if (id == null)
            return responseForm.error("ID cannot be null");
        if (id < 1)
            return responseForm.error("ID cannot be less than 1");

        NetEntity entity = entityRepository.findById(id).orElse(null);

        if (entity == null)
            return responseForm.error("FM with ID " + id + " not found");

        entity.setDeleted(true);
        entityRepository.save(entity);

        NetHistory history = new NetHistory(
                entity,
                null, // spring security system required
                entity.getDomain(),
                entity.getSubnet(),
                entity.getMask(),
                entity.getDns1(),
                entity.getDns2(),
                entity.getDns3(),
                entity.isCloud(),
                entity.getTitle(),
                entity.getDescription(),
                true);
        historyRepository.save(history);

        return responseForm.success("Deleted net: " + entity.getSubnet());
    }

}
