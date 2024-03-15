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

package cloud.robinzon.backend.db.net;

import org.springframework.stereotype.Service;

import cloud.robinzon.backend.db.client.ClientEntity;
import cloud.robinzon.backend.db.client.ClientEntityRepository;
import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class handles the management of Net entities in the database.
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
public class NetEntityManager
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
    private final NetEntityRepository entityRepository;
    private final NetHistoryRepository historyRepository;
    private final NetRentRepository rentRepository;
    private final ClientEntityRepository clientEntityRepository;

    public NetEntityManager(
            NetEntityRepository entityRepository,
            NetHistoryRepository historyRepository,
            NetRentRepository rentRepository,
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
     * @param domain       - domain name {@code 50 chars};
     * @param subnet       - subnet IP {@code 15 chars};
     * @param mask         - mask {@code 15 chars};
     * @param dns1         - 1st DNS IP {@code 15 chars};
     * @param dns2         - 2nd DNS IP {@code 15 chars};
     * @param dns3         - 3th DNS IP {@code 15 chars};
     * @param cloud        - net is in cloud or not {@code 15 chars};
     * @param title        - the short description of the entry {@code 50 chars};
     * @param description  - the full description of the entiy {@code 255 chars};
     * @param clientEntity - renter entity reference;
     * @return A standard response form
     *         that contains the class name,
     *         functions, status and text.
     * @since 2024.03.14
     * @author Anton Kuzmin
     */
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
            domain = domain == null
                    ? ""
                    : domain;
            dns1 = dns1 == null
                    ? ""
                    : dns1;
            dns2 = dns2 == null
                    ? ""
                    : dns2;
            dns3 = dns3 == null
                    ? ""
                    : dns3;
            title = title == null
                    ? ""
                    : title;
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
                            domain.length() > 50
                                    ? setChar("Domain", 50)
                                    : "")
                    .append(
                            subnet == null
                                    ? setNull("Subnet")
                                    : "")
                    .append(
                            entityRepository.checkUniqueSubnet(subnet)
                                    ? String.format("Net with subnet %s already exists", subnet)
                                    : "")
                    .append(
                            subnet.length() > 15
                                    ? setChar("Subnet", 15)
                                    : "")
                    .append(
                            mask == null
                                    ? setNull("Mask")
                                    : "")
                    .append(
                            mask.length() > 15
                                    ? setChar("Mask", 15)
                                    : "")
                    .append(
                            dns1 == null
                                    ? setNull("DNS1")
                                    : "")
                    .append(
                            dns1.length() > 15
                                    ? setChar("DNS1", 15)
                                    : "")
                    .append(
                            dns2 == null
                                    ? setNull("DNS2")
                                    : "")
                    .append(
                            dns2.length() > 15
                                    ? setChar("DNS2", 15)
                                    : "")
                    .append(
                            dns3 == null
                                    ? setNull("DNS3")
                                    : "")
                    .append(
                            dns3.length() > 15
                                    ? setChar("DNS3", 15)
                                    : "")
                    .append(
                            title.length() > 50
                                    ? setChar("Title", 50)
                                    : "")
                    .append(
                            description.length() > 255
                                    ? setChar("Description", 255)
                                    : "")
                    .append(
                            clientEntityRepository.findById(clientEntity.getId()) == null
                                    ? String.format("Client with ID %d not found", clientEntity.getId())
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

            // Adding a new entry to the entity editing history.
            historyRepository.save(
                    new NetHistory(
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
                            false));

            // Adding a new entry to the entity rent history.
            rentRepository.save(
                    new NetRent(
                            entity,
                            clientEntity,
                            null)); // spring security system required

            // The function execution was successful!
            return super.success(
                    inserted(entity.getSubnet()));

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
     * @param id          - the unique identifier of the entity;
     * @param subnet      - subnet IP {@code 15 chars};
     * @param mask        - mask {@code 15 chars};
     * @param dns1        - 1st DNS IP {@code 15 chars};
     * @param dns2        - 2nd DNS IP {@code 15 chars};
     * @param dns3        - 3th DNS IP {@code 15 chars};
     * @param cloud       - net is in cloud or not {@code 15 chars};
     * @param title       - the short description of the entry {@code 50 chars};
     * @param description - the full description of the entiy {@code 255 chars};
     * @return A standard response form
     *         that contains the class name,
     *         functions, status and text.
     * @since 2024.03.14
     * @author Anton Kuzmin
     */
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

        // Setting the function name for the logging class.
        super.function(insert);

        // Searching for an entity by ID in the repository.
        NetEntity entity = entityRepository
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
            domain = domain == null
                    ? ""
                    : domain;
            dns1 = dns1 == null
                    ? ""
                    : dns1;
            dns2 = dns2 == null
                    ? ""
                    : dns2;
            dns3 = dns3 == null
                    ? ""
                    : dns3;
            title = title == null
                    ? ""
                    : title;
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
                            domain.length() > 50
                                    ? setChar("Domain", 50)
                                    : "")
                    .append(
                            subnet == null
                                    ? setNull("Subnet")
                                    : "")
                    .append(
                            entityRepository.checkUniqueSubnet(subnet)
                                    ? String.format("Net with subnet %s already exists", subnet)
                                    : "")
                    .append(
                            subnet.length() > 15
                                    ? setChar("Subnet", 15)
                                    : "")
                    .append(
                            mask == null
                                    ? setNull("Mask")
                                    : "")
                    .append(
                            mask.length() > 15
                                    ? setChar("Mask", 15)
                                    : "")
                    .append(
                            dns1 == null
                                    ? setNull("DNS1")
                                    : "")
                    .append(
                            dns1.length() > 15
                                    ? setChar("DNS1", 15)
                                    : "")
                    .append(
                            dns2 == null
                                    ? setNull("DNS2")
                                    : "")
                    .append(
                            dns2.length() > 15
                                    ? setChar("DNS2", 15)
                                    : "")
                    .append(
                            dns3 == null
                                    ? setNull("DNS3")
                                    : "")
                    .append(
                            dns3.length() > 15
                                    ? setChar("DNS3", 15)
                                    : "")
                    .append(
                            title.length() > 50
                                    ? setChar("Title", 50)
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
                            entity.getDomain().equals(domain)
                                    && entity.getSubnet().equals(subnet)
                                    && entity.getMask().equals(mask)
                                    && entity.getDns1().equals(dns1)
                                    && entity.getDns2().equals(dns2)
                                    && entity.getDns3().equals(dns3)
                                    && entity.isCloud() == cloud
                                    && entity.getTitle().equals(title)
                                    && entity.getDescription().equals(description)
                                            ? String.format("All params of %s is equal", entity.getSubnet())
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
            entity.setDomain(domain);
            entity.setSubnet(subnet);
            entity.setMask(mask);
            entity.setDns1(dns1);
            entity.setDns2(dns2);
            entity.setDns3(dns3);
            entity.setCloud(cloud);
            entity.setTitle(title);
            entity.setDescription(description);
            entityRepository.save(entity);

            historyRepository.save(
                    new NetHistory(
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
                            false));

            // The function execution was successful!
            return super.success(
                    updated(entity.getSubnet()));

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
        NetEntity entity = entityRepository
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
                    new NetHistory(
                            entity,
                            null)); // spring security system required

            // The function execution was successful!
            return super.success(
                    deleted(entity.getSubnet()));

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
