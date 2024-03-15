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
import cloud.robinzon.backend.tools.ResponseStringTemplates;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class handles the management of FM entities in the database.
 * </p>
 * <p>
 * Contains the next methonds:
 * </p>
 * <p>
 * {@code insert} - for insert new entity
 * </p>
 * <p>
 * {@code update} - for update an existing entity
 * </p>
 * <p>
 * {@code delete} - for delete the entity
 * </p>
 * <p>
 * Read more in the attached documents for each method.
 * </p>
 *
 * @since 2024.03.14
 * @author Anton Kuzmin
 */

@Service
public class FmEntityManager
        extends ResponseForm
        implements ResponseStringTemplates {

    /**
     * <h3>Initialize string builder for error collector.</h3>
     * <p>
     * During checks, all error messages will be accumulated here,
     * at the end of the checks the length of this object
     * will be checked and if it is greater than zero,
     * the response form will be returned with the status error
     * </p>
     */
    final StringBuilder err = new StringBuilder();

    // Injecting required repositories.
    private final FmEntityRepository entityRepository;
    private final FmHistoryRepository historyRepository;
    private final FmRentRepository rentRepository;
    private final ClientEntityRepository clientEntityRepository;

    public FmEntityManager(
            FmEntityRepository entityRepository,
            FmHistoryRepository historyRepository,
            FmRentRepository rentRepository,
            ClientEntityRepository clientEntityRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
        this.rentRepository = rentRepository;
        this.clientEntityRepository = clientEntityRepository;

        // Setting the class name for the logging class
        super.set(getClass().getSimpleName());
    }

    /**
     * <h3>Inserts a new entry into the database.</h3>
     * <p>
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the new entity parameters and it will be updated.
     * </p>
     *
     * @param name           the name of the entity {@code 50 chars};
     * @param ip             - the IP address of the entity {@code 15 chars};
     * @param title          - the short description of the entry {@code 50 chars};
     * @param specifications - spicifications of the entiy {@code 255 chars};
     * @param description    - the full description of the entiy {@code 255 chars};
     * @param price          - the price of the entity;
     * @param vm             - FM can host VM's or not;
     * @param clientEntity   - renter entity reference;
     * @return A standard response form
     *         that contains the class name,
     *         functions, status and text.
     * @since 2024.03.14
     * @author Anton Kuzmin
     */
    public ResponseForm insert(
            String name,
            String ip,
            String title,
            String specifications,
            String description,
            int price,
            boolean vm,
            ClientEntity clientEntity) {

        // Setting the function name for the logging class.
        super.function(insert);

        /**
         * This block checks each parameter
         * whether it meets the given requirements.
         */
        try {

            /**
             * Checking strings for null value.
             * If the string is null,
             * it will be replaced with "" (an empty string).
             *
             * IMPORTANT:
             * Don't delete this part of the code!
             * Despite the fact that an empty string takes up more resources than a null
             * and using it in code is less preferable than a null,
             * in this case it is the empty string that is used,
             * since for the database there is no difference
             * between an empty string and a null.
             * For this reason, in entity annotations the default nullable is set to false.
             */
            ip = ip == null
                    ? ""
                    : ip;
            title = title == null
                    ? ""
                    : title;
            specifications = specifications == null
                    ? ""
                    : specifications;

            description = description == null
                    ? ""
                    : description;

            /**
             * Checking strings for compliance with entity requirements
             * and collecting error messages in the stringbuilder
             * initialized at the beginning of the class.
             */
            err
                    .append(
                            name == null
                                    ? setNull("Name")
                                    : "")
                    .append(
                            name.length() > 50
                                    ? setChar("Name", 50)
                                    : "")
                    .append(
                            ip.length() > 50
                                    ? setChar("IP", 15)
                                    : "")
                    .append(
                            entityRepository.checkUniqueIp(ip)
                                    ? String.format("FM with IP %s already exists", ip)
                                    : "")
                    .append(
                            clientEntityRepository.findById(clientEntity.getId()) == null
                                    ? String.format("Client with ID %d not found", clientEntity.getId())
                                    : "")
                    .append(
                            price < 0
                                    ? setLess("Price", 0)
                                    : "")
                    .append(
                            title.length() > 50
                                    ? setChar("Title", 50)
                                    : "")
                    .append(
                            specifications.length() > 255
                                    ? setChar("Specifications", 255)
                                    : "")
                    .append(
                            description.length() > 255
                                    ? setChar("Description", 255)
                                    : "");

            // Termination of the function if errors were detected.
            if (err.length() > 0)
                return super.error(
                        err.toString());

        } catch (Exception e) {
            /**
             * This block of code should not be called at all!
             * If it was called,
             * then the error is guaranteed to be in the code itself,
             * and not in the input data.
             */

            e.printStackTrace();
            return super.error("An unspecified error occurred during checks");
        }

        /**
         * This part of the function is executed
         * only if all checks have passed successfully
         * and no errors have been detected.
         */
        try {

            // Creating a new entity
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

            // Adding a new entry to the entity editing history.
            historyRepository.save(
                    new FmHistory(
                            entity,
                            name,
                            ip,
                            title,
                            specifications,
                            description,
                            price,
                            vm,
                            null, // spring security system required
                            false));

            // Adding a new entry to the entity rent history.
            rentRepository.save(
                    new FmRent(
                            entity,
                            clientEntity,
                            null)); // spring security system required

            // The function execution was successful!
            return super.success(
                    inserted(entity.getName()));

        } catch (Exception e) {
            /**
             * This block of code should not be called at all!
             * If it was called,
             * then the error is guaranteed to be in the code itself,
             * and not in the input data.
             */

            e.printStackTrace();
            return super.error(
                    "An unspecified error occurred while making changes to the repositories");
        }
    }

    /**
     * <h3>Updates an existing entry in the database.</h3>
     * <p>
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the entity ID and new parameters and it will be updated.
     * </p>
     *
     * @param id             - the unique identifier of the entity;
     * @param ip             - the IP address of the entity {@code 15 chars};
     * @param title          - the short description of the entry {@code 50 chars};
     * @param specifications - spicifications of the entiy {@code 255 chars};
     * @param description    - the full description of the entiy {@code 255 chars};
     * @param price          - the price of the entity;
     * @param vm             - FM can host VM's or not;
     * @return A standard response form
     *         that contains the class name,
     *         functions, status and text.
     * @since 2024.03.14
     * @author Anton Kuzmin
     */
    public ResponseForm update(
            Long id,
            String name,
            String ip,
            String title,
            String specifications,
            String description,
            int price,
            boolean vm) {

        // Setting the function name for the logging class.
        super.function(insert);

        // Searching for an entity by ID in the repository.
        FmEntity entity = entityRepository
                .findById(id)
                .orElse(null);

        /**
         * This block checks each parameter
         * whether it meets the given requirements.
         */
        try {

            /**
             * Checking strings for null value.
             * If the string is null,
             * it will be replaced with "" (an empty string).
             *
             * IMPORTANT:
             * Don't delete this part of the code!
             * Despite the fact that an empty string takes up more resources than a null
             * and using it in code is less preferable than a null,
             * in this case it is the empty string that is used,
             * since for the database there is no difference
             * between an empty string and a null.
             * For this reason, in entity annotations the default nullable is set to false.
             */
            ip = ip == null
                    ? ""
                    : ip;
            title = title == null
                    ? ""
                    : title;
            specifications = specifications == null
                    ? ""
                    : specifications;

            description = description == null
                    ? ""
                    : description;

            /**
             * Checking strings for compliance with entity requirements
             * and collecting error messages in the stringbuilder
             * initialized at the beginning of the class.
             */
            err
                    .append(
                            name == null
                                    ? setNull("Name")
                                    : "")
                    .append(
                            name.length() > 50
                                    ? setChar("Name", 50)
                                    : "")
                    .append(
                            ip.length() > 50
                                    ? setChar("IP", 15)
                                    : "")
                    .append(
                            entityRepository.checkUniqueIp(ip)
                                    ? String.format("FM with IP %s already exists", ip)
                                    : "")
                    .append(
                            price < 0
                                    ? setLess("Price", 0)
                                    : "")
                    .append(
                            title.length() > 50
                                    ? setChar("Title", 50)
                                    : "")
                    .append(
                            specifications.length() > 255
                                    ? setChar("Specifications", 255)
                                    : "")
                    .append(
                            description.length() > 255
                                    ? setChar("Description", 255)
                                    : "")

                    /**
                     * Checking for the presence of an entity in the database.
                     * If the entity is not in the database,
                     * a message about a non-existent entity will be added to the error list
                     * and the function will be interrupted.
                     */
                    .append(
                            entity == null
                                    ? "Entity with ID " + id + " not found"
                                    : "")

                    /**
                     * Compare each entity parameter with the new input data.
                     * If all the data is the same,
                     * then there is no point in making changes
                     * and adding a new entry to the history,
                     * so a new message will be added about this event as an error.
                     */
                    .append(
                            entity.getName().equals(name)
                                    && entity.getIp().equals(ip)
                                    && entity.getTitle().equals(title)
                                    && entity.getSpecifications().equals(specifications)
                                    && entity.getDescription().equals(description)
                                    && entity.getPrice() == price
                                    && entity.getVm() == vm
                                            ? String.format("All params of %s is equal", entity.getName())
                                            : "");

            // Termination of the function if errors were detected.
            if (err.length() > 0)
                return super.error(
                        err.toString());

        } catch (Exception e) {
            /**
             * This block of code should not be called at all!
             * If it was called,
             * then the error is guaranteed to be in the code itself,
             * and not in the input data.
             */

            e.printStackTrace();
            return super.error("An unspecified error occurred during checks");
        }

        /**
         * This part of the function is executed
         * only if all checks have passed successfully
         * and no errors have been detected.
         */
        try {

            // Setting new values.
            entity.setName(name);
            entity.setIp(ip);
            entity.setTitle(title);
            entity.setSpecifications(specifications);
            entity.setDescription(description);
            entity.setPrice(price);
            entity.setVm(vm);
            entityRepository.save(entity);

            // Adding a new entry to the entity editing history.
            historyRepository.save(
                    new FmHistory(
                            entity,
                            name,
                            ip,
                            title,
                            specifications,
                            description,
                            price,
                            vm,
                            null, // spring security system required
                            false));

            // The function execution was successful!
            return super.success(
                    updated(entity.getName()));

        } catch (Exception e) {
            /**
             * This block of code should not be called at all!
             * If it was called,
             * then the error is guaranteed to be in the code itself,
             * and not in the input data.
             */

            e.printStackTrace();
            return super.error(
                    "An unspecified error occurred while making changes to the repositories");
        }
    }

    /**
     * <h3>Deleting an existing entry in the database.</h3>
     * <p>
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the entity ID and new parameters and it will be updated.
     * </p>
     *
     * @param id - the unique identifier of the entity;
     * @return A standard response form
     *         that contains the class name,
     *         functions, status and text.
     * @since 2024.03.14
     * @author Anton Kuzmin
     */
    public ResponseForm delete(Long id) {

        // Setting the function name for the logging class.
        super.function(delete);

        // Searching for an entity by ID in the repository.
        FmEntity entity = entityRepository
                .findById(id)
                .orElse(null);

        /**
         * This block checks each parameter
         * whether it meets the given requirements.
         */
        try {

            /**
             * Checking strings for compliance with entity requirements
             * and collecting error messages in the stringbuilder
             * initialized at the beginning of the class.
             */
            err
                    .append(
                            id == null
                                    ? setNull("ID")
                                    : "")
                    .append(
                            id < 1
                                    ? setLess("ID", 1)
                                    : "")

                    /**
                     * Checking for the presence of an entity in the database.
                     * If the entity is not in the database,
                     * a message about a non-existent entity will be added to the error list
                     * and the function will be interrupted.
                     */
                    .append(
                            entity == null
                                    ? String.format("Entity with ID %d not found", id)
                                    : "")

                    /**
                     * Compare each entity parameter with the new input data.
                     * If all the data is the same,
                     * then there is no point in making changes
                     * and adding a new entry to the history,
                     * so a new message will be added about this event as an error.
                     */
                    .append(
                            entity.isDeleted() == true
                                    ? String.format("Entity with ID %d already deleted", entity.getId())
                                    : "");

            // Termination of the function if errors were detected.
            if (err.length() > 0)
                return super.error(
                        err.toString());

        } catch (Exception e) {
            /**
             * This block of code should not be called at all!
             * If it was called,
             * then the error is guaranteed to be in the code itself,
             * and not in the input data.
             */

            e.printStackTrace();
            return super.error("An unspecified error occurred during checks");
        }

        /**
         * This part of the function is executed
         * only if all checks have passed successfully
         * and no errors have been detected.
         */
        try {

            // Setting new values.
            entity.setDeleted(true);
            entityRepository.save(entity);

            // Adding a new entry to the entity editing history.
            historyRepository.save(
                    new FmHistory(
                            entity,
                            null)); // spring security system required

            // The function execution was successful!
            return super.success(
                    deleted(entity.getName()));

        } catch (Exception e) {
            /**
             * This block of code should not be called at all!
             * If it was called,
             * then the error is guaranteed to be in the code itself,
             * and not in the input data.
             */

            e.printStackTrace();
            return super.error(
                    "An unspecified error occurred while making changes to the repositories");
        }
    }

}
