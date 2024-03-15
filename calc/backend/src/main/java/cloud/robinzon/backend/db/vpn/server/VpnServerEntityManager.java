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

package cloud.robinzon.backend.db.vpn.server;

import java.util.Set;

import org.springframework.stereotype.Service;

import cloud.robinzon.backend.db.net.NetEntity;
import cloud.robinzon.backend.db.net.NetEntityRepository;
import cloud.robinzon.backend.settings.vpn.type.VpnTypeEntity;
import cloud.robinzon.backend.tools.ResponseForm;
import cloud.robinzon.backend.tools.ResponseStringTemplates;

/**
 * <h3>Entity Management Tools</h3>
 * <p>
 * This class handles the management of VPN server entities in the database.
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
 * @since 2024.03.13
 * @author Anton Kuzmin
 */

@Service
public final class VpnServerEntityManager
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
	private final VpnServerEntityRepository entityRepository;
	private final VpnServerHistoryRepository historyRepository;
	private final NetEntityRepository netEntityRepository;

	public VpnServerEntityManager(
			VpnServerEntityRepository entityRepository,
			VpnServerHistoryRepository historyRepository,
			NetEntityRepository netEntityRepository) {
		this.entityRepository = entityRepository;
		this.historyRepository = historyRepository;
		this.netEntityRepository = netEntityRepository;

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
	 * @param title         - the short description of the entry {@code 50 chars};
	 * @param description   - the full description of the entiy {@code 255 chars};
	 * @param ip            - the IP address {@code 15 chars};
	 * @param publicKey     - the public key {@code 50 chars};
	 * @param netEntity     - the NetEntity reference;
	 * @param vpnTypeEntity - the VPN type entity-list;
	 * @return A standard response form
	 *         that contains the class name,
	 *         functions, status and text.
	 * @since 2024.03.13
	 * @author Anton Kuzmin
	 */
	public ResponseForm insert(
			String title,
			String description,
			String ip,
			String publicKey,
			NetEntity netEntity,
			Set<VpnTypeEntity> vpnTypeEntity) {

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
			title = title == null
					? ""
					: title;
			description = description == null
					? ""
					: description;
			publicKey = publicKey == null
					? ""
					: publicKey;

			/**
			 * Checking strings for compliance with entity requirements
			 * and collecting error messages in the stringbuilder
			 * initialized at the beginning of the class.
			 */
			err
					.append(
							title.length() > 50
									? setChar("Title", 50)
									: "")
					.append(
							description.length() > 255
									? setChar("Description", 255)
									: "")
					.append(
							ip == null
									? setNull("IP")
									: "")
					.append(
							ip.length() > 15
									? setChar("IP", 15)
									: "")
					.append(
							publicKey.length() > 50
									? setChar("Public key", 50)
									: "")
					.append(
							netEntity == null
									? setNull("Net entity")
									: "")
					.append(
							netEntityRepository.findById(netEntity.getId()) == null
									? String.format("Net with ID %d not found", netEntity.getId())
									: "")
					.append(
							vpnTypeEntity.size() == 0
									? setLess("VPN type entities", 1)
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
			VpnServerEntity entity = new VpnServerEntity(
					title,
					description,
					ip,
					publicKey,
					netEntity,
					vpnTypeEntity);
			entityRepository.save(entity);

			// Adding a new entry to the entity editing history.
			historyRepository.save(
					new VpnServerHistory(
							entity,
							title,
							description,
							ip,
							publicKey,
							netEntity,
							vpnTypeEntity,
							null, // Spring security system required
							false));

			// The function execution was successful!
			return super.success(
					inserted(entity.getIp()));

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
	 * @param id            - the unique identifier of the entity;
	 * @param title         - the short description of the entiry {@code 50 chars};
	 * @param description   - the full description of the entity {@code 255 chars};
	 * @param ip            - the IP address {@code 15 chars};
	 * @param publicKey     - the public key {@code 50 chars};
	 * @param netEntity     - the reference to a NetEntity object;
	 * @param vpnTypeEntity - the list of VPN type entities;
	 * @return A standard response form
	 *         that contains the class name,
	 *         functions, status and text.
	 * @since 2024.03.13
	 * @author Anton Kuzmin
	 */
	public ResponseForm update(
			Long id,
			String title,
			String description,
			String ip,
			String publicKey,
			NetEntity netEntity,
			Set<VpnTypeEntity> vpnTypeEntity) {

		// Setting the function name for the logging class.
		super.function(update);

		// Searching for an entity by ID in the repository.
		VpnServerEntity entity = entityRepository
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
			publicKey = publicKey == null
					? ""
					: publicKey;

			/**
			 * Checking strings for compliance with entity requirements
			 * and collecting error messages in the stringbuilder
			 * initialized at the beginning of the class.
			 */
			err
					.append(
							title.length() > 50
									? setChar("\nTitle", 50)
									: "")
					.append(
							description.length() > 255
									? setChar("\nDescription", 255)
									: "")
					.append(
							ip == null
									? setNull("IP")
									: "")
					.append(
							ip.length() > 15
									? setChar("IP", 15)
									: "")
					.append(
							publicKey.length() > 50
									? setChar("Public key", 50)
									: "")
					.append(
							netEntity == null
									? setNull("Net entity")
									: "")
					.append(
							netEntityRepository.findById(netEntity.getId()) == null
									? String.format("Net with ID %d not found", netEntity.getId())
									: "")
					.append(
							vpnTypeEntity.size() == 0
									? setLess("VPN type entities", 1)
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
							entity.getTitle().equals(title)
									&& entity.getDescription().equals(description)
									&& entity.getIp().equals(ip)
									&& entity.getPublicKey().equals(publicKey)
									&& entity.getNetEntity().getId().equals(netEntity.getId())
									&& entity.getVpnTypeEntity().equals(vpnTypeEntity)
											? String.format("All params of %s is equal", entity.getIp())
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
			entity.setIp(ip);
			entity.setPublicKey(publicKey);
			entity.setNetEntity(netEntity);
			entity.setVpnTypeEntity(vpnTypeEntity);
			entityRepository.save(entity);

			// Adding a new entry to the entity editing history.
			historyRepository.save(
					new VpnServerHistory(
							entity,
							title,
							description,
							ip,
							publicKey,
							netEntity,
							vpnTypeEntity,
							null, // spring security system required
							false));

			// The function execution was successful!
			return super.success(
					updated(entity.getIp()));

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
	 * @since 2024.03.13
	 * @author Anton Kuzmin
	 */
	public ResponseForm delete(Long id) {

		// Setting the function name for the logging class.
		super.function(delete);

		// Searching for an entity by ID in the repository.
		VpnServerEntity entity = entityRepository
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
				new VpnServerHistory(
					entity,
					null)); // spring security system required

			// The function execution was successful!
			return super.success(
					deleted(entity.getIp()));

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