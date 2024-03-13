/**
 * Copyright 2024 Anton Kuzmin http://github.com/antonkuzmn1
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cloud.robinzon.backend.db.fm;

import org.springframework.stereotype.Service;

import cloud.robinzon.backend.db.client.ClientEntity;
import cloud.robinzon.backend.db.client.ClientEntityRepository;
import cloud.robinzon.backend.tools.ResponseForm;

@Service
public class FmEntityManager {

    private FmEntityRepository entityRepository;
    private FmHistoryRepository historyRepository;
    private FmRentRepository rentRepository;
    private ClientEntityRepository clientEntityRepository;

    public FmEntityManager(
            FmEntityRepository entityRepository,
            FmHistoryRepository historyRepository,
            FmRentRepository rentRepository,
            ClientEntityRepository clientEntityRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
        this.rentRepository = rentRepository;
        this.clientEntityRepository = clientEntityRepository;
    }

    private ResponseForm responseForm = new ResponseForm("FmEntityManager");

    public ResponseForm insert(
            String name,
            String ip,
            String title,
            String specifications,
            String description,
            int price,
            boolean vm,
            ClientEntity clientEntity) {
        responseForm.function("insert");

        if (name == null)
            return responseForm.error("Name cannot be null");
        if (name.length() > 50)
            return responseForm.error("Name cannot contain more than 50 characters");
        if (ip == null)
            ip = "";
        if (ip.length() > 15)
            return responseForm.error("IP cannot contain more than 15 characters");
        if (entityRepository.checkUniqueIp(ip))
            return responseForm.error("FM with IP " + ip + " already exists");
        if (title == null)
            title = "";
        if (title.length() > 50)
            return responseForm.error("Title cannot contain more than 15 characters");
        if (specifications == null)
            specifications = "";
        if (specifications.length() > 255)
            return responseForm.error("Specifications cannot contain more than 255 characters");
        if (description == null)
            description = "";
        if (description.length() > 255)
            return responseForm.error("Description cannot contain more than 255 characters");
        if (price < 0)
            return responseForm.error("Price cannot be less than 0");
        if (clientEntityRepository.findById(clientEntity.getId()).orElse(null) == null)
            return responseForm.error("Client with ID " + clientEntity.getId() + " not found");

        FmEntity entity = new FmEntity(
                name,
                ip,
                title,
                specifications,
                description,
                price,
                vm,
                clientEntity);
        entityRepository.save(entity);

        FmHistory history = new FmHistory(
                entity,
                name,
                ip,
                title,
                specifications,
                description,
                price,
                vm,
                null, // spring security system required
                false);
        historyRepository.save(history);

        FmRent rent = new FmRent(
                entity,
                clientEntity,
                null); // spring security system required
        rentRepository.save(rent);

        return responseForm.success("Inserted new FM: " + entity.getName());
    }

    public ResponseForm update(
            Long id,
            String name,
            String ip,
            String title,
            String specifications,
            String description,
            int price,
            boolean vm) {
        responseForm.function("update");

        if (name == null)
            return responseForm.error("Name cannot be null");
        if (name.length() > 50)
            return responseForm.error("Name cannot contain more than 50 characters");
        if (ip == null)
            ip = "";
        if (ip.length() > 15)
            return responseForm.error("IP cannot contain more than 15 characters");
        if (entityRepository.checkUniqueIp(ip))
            return responseForm.error("FM with IP " + ip + " already exists");
        if (title == null)
            title = "";
        if (title.length() > 50)
            return responseForm.error("Title cannot contain more than 15 characters");
        if (specifications == null)
            specifications = "";
        if (specifications.length() > 255)
            return responseForm.error("Specifications cannot contain more than 255 characters");
        if (description == null)
            description = "";
        if (description.length() > 255)
            return responseForm.error("Description cannot contain more than 255 characters");
        if (price < 0)
            return responseForm.error("Price cannot be less than 0");

        FmEntity entity = entityRepository.findById(id).orElse(null);

        if (entity == null)
            return responseForm.error("FM with ID " + id + " not found");

        if (entity.getName().equals(name)
                && entity.getIp().equals(ip)
                && entity.getTitle().equals(title)
                && entity.getSpecifications().equals(specifications)
                && entity.getDescription().equals(description)
                && entity.getPrice() == price
                && entity.getVm() == vm)
            return responseForm.error("All params of " + entity.getName() + " is equal");

        entity.setName(name);
        entity.setIp(ip);
        entity.setTitle(title);
        entity.setSpecifications(specifications);
        entity.setDescription(description);
        entity.setPrice(price);
        entity.setVm(vm);
        entityRepository.save(entity);

        FmHistory history = new FmHistory(
                entity,
                name,
                ip,
                title,
                specifications,
                description,
                price,
                vm,
                null, // spring security system required
                false);
        historyRepository.save(history);

        return responseForm.success("Updated FM: " + entity.getName());
    }

    public ResponseForm delete(Long id) {
        responseForm.function("delete");

        if (id == null)
            return responseForm.error("ID cannot be null");
        if (id < 1)
            return responseForm.error("ID cannot be less than 1");

        FmEntity entity = entityRepository.findById(id).orElse(null);

        if (entity == null)
            return responseForm.error("FM with ID " + id + " not found");

        entity.setDeleted(true);
        entityRepository.save(entity);

        FmHistory history = new FmHistory(
                entity,
                entity.getName(),
                entity.getIp(),
                entity.getTitle(),
                entity.getSpecifications(),
                entity.getDescription(),
                entity.getPrice(),
                entity.getVm(),
                null, // spring security system required
                true);
        historyRepository.save(history);

        return responseForm.success("Deleted client: " + entity.getName());
    }

}
