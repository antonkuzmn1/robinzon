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

package cloud.robinzon.backend.db.reg;

import java.util.Date;

import org.springframework.stereotype.Service;

import cloud.robinzon.backend.tools.ResponseForm;

@Service
public class RegEntityManager {

    private RegEntityRepository entityRepository;
    private RegHistoryRepository historyRepository;

    public RegEntityManager(
            RegEntityRepository entityRepository,
            RegHistoryRepository historyRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
    }

    private ResponseForm responseForm = new ResponseForm("RegEntityManager");

    public ResponseForm insert(
            String brand,
            String name,
            String part,
            String serial,
            Date buyDate,
            int warrantyMonths,
            String provider,
            String title,
            String description) {
        responseForm.function("insert");

        if (brand == null)
            brand = "";
        if (brand.length() > 100)
            return responseForm.error("Brand cannot contain more than 100 characters");
        if (name == null)
            return responseForm.error("Name cannot be null");
        if (name.length() > 100)
            return responseForm.error("Name cannot contain more than 100 characters");
        if (part == null)
            part = "";
        if (part.length() > 50)
            return responseForm.error("Part cannot contain more than 50 characters");
        if (serial == null)
            serial = "";
        if (serial.length() > 50)
            return responseForm.error("Serial cannot contain more than 50 characters");
        if (buyDate == null)
            return responseForm.error("Buy date cannot be null");
        if (warrantyMonths < 0)
            return responseForm.error("Warranty months cannot be less than 0");
        if (provider == null)
            provider = "";
        if (provider.length() > 50)
            return responseForm.error("Provider cannot contain more than 50 characters");
        if (title == null)
            title = "";
        if (title.length() > 50)
            return responseForm.error("Title cannot contain more than 50 characters");
        if (description == null)
            description = "";
        if (description.length() > 255)
            return responseForm.error("Description cannot contain more than 255 characters");

        RegEntity entity = new RegEntity(
                brand,
                name,
                part,
                serial,
                buyDate,
                warrantyMonths,
                provider,
                title,
                description);
        entityRepository.save(entity);

        RegHistory history = new RegHistory(
                entity,
                brand,
                name,
                part,
                serial,
                buyDate,
                warrantyMonths,
                provider,
                title,
                description,
                null, // spring security system required
                false);
        historyRepository.save(history);

        return responseForm.success("Inserted new reg: " + entity.getName());
    }

    public ResponseForm update(
            Long id,
            String brand,
            String name,
            String part,
            String serial,
            Date buyDate,
            int warrantyMonths,
            String provider,
            String title,
            String description) {
        responseForm.function("insert");

        if (brand == null)
            brand = "";
        if (brand.length() > 100)
            return responseForm.error("Brand cannot contain more than 100 characters");
        if (name == null)
            return responseForm.error("Name cannot be null");
        if (name.length() > 100)
            return responseForm.error("Name cannot contain more than 100 characters");
        if (part == null)
            part = "";
        if (part.length() > 50)
            return responseForm.error("Part cannot contain more than 50 characters");
        if (serial == null)
            serial = "";
        if (serial.length() > 50)
            return responseForm.error("Serial cannot contain more than 50 characters");
        if (buyDate == null)
            return responseForm.error("Buy date cannot be null");
        if (warrantyMonths < 0)
            return responseForm.error("Warranty months cannot be less than 0");
        if (provider == null)
            provider = "";
        if (provider.length() > 50)
            return responseForm.error("Provider cannot contain more than 50 characters");
        if (title == null)
            title = "";
        if (title.length() > 50)
            return responseForm.error("Title cannot contain more than 50 characters");
        if (description == null)
            description = "";
        if (description.length() > 255)
            return responseForm.error("Description cannot contain more than 255 characters");

        RegEntity entity = entityRepository.findById(id).orElse(null);

        if (entity == null)
            return responseForm.error("Reg with ID " + id + " not found");

        if (entity.getBrand().equals(brand)
                && entity.getName().equals(name)
                && entity.getPart().equals(part)
                && entity.getSerial().equals(serial)
                && entity.getBuyDate().equals(buyDate)
                && entity.getWarrantyMonths() == warrantyMonths
                && entity.getProvider().equals(provider)
                && entity.getTitle().equals(title)
                && entity.getDescription().equals(description))
            return responseForm.error("All params of " + entity.getName() + " is equal");

        entity.setBrand(brand);
        entity.setName(name);
        entity.setPart(part);
        entity.setSerial(serial);
        entity.setBuyDate(buyDate);
        entity.setWarrantyMonths(warrantyMonths);
        entity.setProvider(provider);
        entity.setTitle(title);
        entity.setDescription(description);
        entityRepository.save(entity);

        RegHistory history = new RegHistory(
                entity,
                brand,
                name,
                part,
                serial,
                buyDate,
                warrantyMonths,
                provider,
                title,
                description,
                null, // spring security system required
                false);
        historyRepository.save(history);

        return responseForm.success("Updated reg: ");
    }

    public ResponseForm delete(Long id) {
        responseForm.function("delete");

        if (id == null)
            return responseForm.error("ID cannot be null");
        if (id < 1)
            return responseForm.error("ID cannot be less than 1");

        RegEntity entity = entityRepository.findById(id).orElse(null);

        if (entity == null)
            return responseForm.error("Reg with ID " + id + " not found");

        entity.setDeleted(true);
        entityRepository.save(entity);

        RegHistory history = new RegHistory(
                entity,
                entity.getBrand(),
                entity.getName(),
                entity.getPart(),
                entity.getSerial(),
                entity.getBuyDate(),
                entity.getWarrantyMonths(),
                entity.getProvider(),
                entity.getTitle(),
                entity.getDescription(),
                null, // spring security system required
                true);
        historyRepository.save(history);

        return responseForm.success("Deleted reg: ");
    }

}
