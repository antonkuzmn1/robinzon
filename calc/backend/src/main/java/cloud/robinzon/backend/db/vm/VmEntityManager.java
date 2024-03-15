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

package cloud.robinzon.backend.db.vm;

import org.springframework.stereotype.Service;

import cloud.robinzon.backend.db.fm.FmEntity;
import cloud.robinzon.backend.db.fm.FmEntityRepository;
import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class handles the management of VM entities in the database.
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
public class VmEntityManager
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
    private final VmEntityRepository entityRepository;
    private final VmHistoryRepository historyRepository;
    private final VmRentRepository rentRepository;
    private final FmEntityRepository fmEntityRepository;

    public VmEntityManager(
            VmEntityRepository entityRepository,
            VmHistoryRepository historyRepository,
            VmRentRepository rentRepository,
            FmEntityRepository fmEntityRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
        this.rentRepository = rentRepository;
        this.fmEntityRepository = fmEntityRepository;

        // Setting the class name for the logging class
        super.set(getClass().getSimpleName());
    }

    /**
     * <h3>Inserts a new entry into the database.</h3>
     * <p>
     * <strong>IMPORTANT:</strong>
     * </p>
     * <p>
     * If entity already exists in database,
     * it will be just updated.
     * keep calm :)
     * </p>
     * <p>
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the new entity parameters and it will be updated.
     * </p>
     *
     * @param id          - unique identifier of the entry {@code 36 chars};
     * @param name        - name of the entry {@code 50 chars};
     * @param cpu         - amount of CPU cores;
     * @param ram         - amount of RAM;
     * @param ssd         - amount of SSD;
     * @param hdd         - amount of HDD;
     * @param running     - VM's state;
     * @param title       - short description of the entity {@code 50 chars};
     * @param description - full description of the entity {@code 255 chars};
     * @param fmEntity    - the fm entity on which this virtual machine is hosted;
     * @return A standard response form
     *         that contains the class name,
     *         functions, status and text.
     * @since 2024.03.14
     * @author Anton Kuzmin
     */
    public ResponseForm insert(
            String id,
            String name,
            int cpu,
            int ram,
            int ssd,
            int hdd,
            boolean running,
            FmEntity fmEntity) {

        // Setting the function name for the logging class.
        super.function(insert);

        // Searching for an entity by ID in the repository.
        VmEntity entity = entityRepository
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
                            id.length() > 36
                                    ? setMore("ID", 36)
                                    : "")
                    .append(
                            name == null
                                    ? setNull("Name")
                                    : "")
                    .append(
                            name.length() > 100
                                    ? setChar("Name", 100)
                                    : "")
                    .append(
                            cpu < 1
                                    ? setLess("CPU", 1)
                                    : "")
                    .append(
                            cpu > 64
                                    ? setMore("CPU", 64)
                                    : "")
                    .append(
                            ram < 0
                                    ? setLess("RAM", 0)
                                    : "")
                    .append(
                            ram > 256
                                    ? setMore("RAM", 256)
                                    : "")
                    .append(
                            ssd < 0
                                    ? setLess("SSD", 0)
                                    : "")
                    .append(
                            ssd > 10000
                                    ? setMore("SSD", 10000)
                                    : "")
                    .append(
                            hdd < 0
                                    ? setLess("HDD", 0)
                                    : "")
                    .append(
                            hdd > 50000
                                    ? setMore("HDD", 50000)
                                    : "")
                    .append(
                            fmEntity == null
                                    ? setNull("FM")
                                    : "")
                    .append(
                            fmEntityRepository.findById(fmEntity.getId()) == null
                                    ? String.format(
                                            "FM with ID %d not found",
                                            entity.getId())
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
                                    && entity.getCpu() == cpu
                                    && entity.getRam() == ram
                                    && entity.getSsd() == ssd
                                    && entity.getHdd() == hdd
                                    && entity.isRunning() == running
                                    && entity.getFmEntity().equals(fmEntity)
                                            ? String.format(
                                                    "All params of %s is equal",
                                                    entity.getName())
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

            if (entity.equals(null)) {

                // Creating a new entity.
                VmEntity entityNew = new VmEntity(
                        id,
                        name,
                        cpu,
                        ram,
                        ssd,
                        hdd,
                        running,
                        fmEntity);
                entityRepository.save(entity);

                // Adding a new entry to the entity editing history.
                historyRepository.save(
                        new VmHistory(
                                entityNew,
                                name,
                                cpu,
                                ram,
                                ssd,
                                hdd,
                                running,
                                "",
                                "",
                                fmEntity,
                                null, // no need cuz it change by system
                                false));

                // Creating a rent history for entity.
                rentRepository.save(
                        new VmRent(
                                entityNew,
                                null,
                                null)); // no need cuz it change by system

                // The function execution was successful!
                return super.success(
                        inserted(entityNew.getName()));
            }

            // Setting new values.
            entity.setName(name);
            entity.setCpu(cpu);
            entity.setRam(ram);
            entity.setSsd(ssd);
            entity.setHdd(hdd);
            entity.setRunning(running);
            entity.setFmEntity(fmEntity);
            entityRepository.save(entity);

            // Adding a new entry to the entity editing history.
            historyRepository.save(
                    new VmHistory(
                            entity,
                            name,
                            cpu,
                            ram,
                            ssd,
                            hdd,
                            running,
                            entity.getTitle(),
                            entity.getDescription(),
                            fmEntity,
                            null, // no need cuz it change by system
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
     * @param id          - unique identifier of the entry {@code 36 chars};
     * @param title       - the short description of the entiry {@code 50 chars};
     * @param description - the full description of the entity {@code 255 chars};
     * @return A standard response form
     *         that contains the class name,
     *         functions, status and text.
     * @since 2024.03.14
     * @author Anton Kuzmin
     */
    public ResponseForm update(
            String id,
            String title,
            String description) {

        // Setting the function name for the logging class.
        super.function(update);

        // Searching for an entity by ID in the repository.
        VmEntity entity = entityRepository
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
                            id == null
                                    ? setNull("ID")
                                    : "")
                    .append(
                            id.length() > 36
                                    ? setMore("ID", 36)
                                    : "")
                    .append(
                            title.length() > 50
                                    ? setChar("\nTitle", 50)
                                    : "")
                    .append(
                            description.length() > 255
                                    ? setChar("\nDescription", 255)
                                    : "")

                    /**
                     * Checking for the presence of an entity in the database.
                     * If the entity is not in the database,
                     * a message about a non-existent entity will be added to the error list
                     * and the function will be interrupted.
                     */
                    .append(
                            entity == null
                                    ? String.format("VM with ID %d not found", id)
                                    : "")

                    /**
                     * Compare each entity parameter with the new input data.
                     * If all the data is the same,
                     * then there is no point in making changes
                     * and adding a new entry to the history,
                     * so a new message will be added about this event as an error.
                     */
                    .append(
                            entity.getTitle().equals(title)
                                    && entity.getDescription().equals(description)
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
            entity.setTitle(title);
            entity.setDescription(description);
            entityRepository.save(entity);

            // Adding a new entry to the entity editing history.
            historyRepository.save(
                    new VmHistory(
                            entity,
                            id,
                            entity.getCpu(),
                            entity.getRam(),
                            entity.getSsd(),
                            entity.getHdd(),
                            entity.isRunning(),
                            title,
                            description,
                            entity.getFmEntity(),
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

    // public ResponseForm delete() {
    // super.function("delete");
    // return super.error("Function not working");
    // }

    /**
     * <h3>Soft deletion of all entities from the database.</h3>
     * <p>
     * The function implements all the necessary checks
     * for compliance with data types,allowed string lengths, etc.
     * The function will also perform all necessary actions
     * with the edit history repository
     * and the rental history repository (if present),
     * just pass the entity ID and new parameters and it will be updated.
     * </p>
     *
     * @return A standard response form
     *         that contains the class name,
     *         functions, status and text.
     * @since 2024.03.14
     * @author Anton Kuzmin
     */
    public ResponseForm deleteAll() {

        // Setting the function name for the logging class.
        super.function("deleteAll");

        /**
         * This part of the function is executed
         * only if all checks have passed successfully
         * and no errors have been detected.
         */
        try {

            // Setting new values.
            entityRepository.markAsDeletedAll();

            // The function execution was successful!
            return super.success("All VM has been succefuly marked as deleted");

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
