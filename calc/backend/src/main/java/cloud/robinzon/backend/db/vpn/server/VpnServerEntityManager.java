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
import cloud.robinzon.backend.tools.ResponseTemplates;

/**
 * This class handles the management of VPN server entities in the database.
 */

@Service
public final class VpnServerEntityManager
        extends ResponseForm
        implements ResponseTemplates {

    /**
     * Initialize string builder for error collector
     */
    final StringBuilder err = new StringBuilder();

    private final VpnServerEntityRepository entityRepository;
    private final VpnServerHistoryRepository historyRepository;
    private final NetEntityRepository netEntityRepository;

    /**
     * Constructor for VpnServerEntityManager class.
     * 
     * @param entityRepository    the repository for VPN server entities
     * @param historyRepository   the repository for VPN server history
     * @param netEntityRepository the repository for NetEntityпока
     */
    public VpnServerEntityManager(
            VpnServerEntityRepository entityRepository,
            VpnServerHistoryRepository historyRepository,
            NetEntityRepository netEntityRepository) {
        this.entityRepository = entityRepository;
        this.historyRepository = historyRepository;
        this.netEntityRepository = netEntityRepository;
        super.set(getClass().getSimpleName());
    }

    /**
     * Inserts a new entry into the database.
     * 
     * @param title         the short description of the entry (50 chars)
     * @param description   the full description of the entiy (255 chars)
     * @param ip            the IP address (15 chars)
     * @param publicKey     the public key (50 chars)
     * @param netEntity     the NetEntity reference
     * @param vpnTypeEntity the VPN type entity-list
     * @return a standard response form
     *         that contains the class name,
     *         functions, status and text
     * @author Anton Kuzmin
     * @since 2023.03.13
     */
    public ResponseForm insert(
            String title,
            String description,
            String ip,
            String publicKey,
            NetEntity netEntity,
            Set<VpnTypeEntity> vpnTypeEntity) {
        super.function(insert);

        // DEFAULTS
        title = title == null
                ? ""
                : title;
        description = description == null
                ? ""
                : description;
        publicKey = publicKey == null
                ? ""
                : publicKey;

        // CHECKINGS
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
                        !netEntityRepository.checkUniqueSubnet(netEntity.getSubnet())
                                ? "Net with ID " + netEntity.getId() + " not found"
                                : "")
                .append(
                        vpnTypeEntity.size() == 0
                                ? setLess("VPN type entities", 1)
                                : "");
        if (err.length() > 0)
            return super.error(
                    err.toString());

        // SAVINGS
        VpnServerEntity entity = new VpnServerEntity(
                title,
                description,
                ip,
                publicKey,
                netEntity,
                vpnTypeEntity);
        entityRepository.save(entity);

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

        return super.success(
                inserted(
                        "VPN server",
                        entity.getIp()));
    }

    /**
     * Updates an existing entry in the database.
     * 
     * @param id            the unique identifier of the entity
     * @param title         the short description of the entiry (50 chars)
     * @param description   the full description of the entity (255 chars)
     * @param ip            the IP address (15 chars)
     * @param publicKey     the public key (50 chars)
     * @param netEntity     the reference to a NetEntity object
     * @param vpnTypeEntity the list of VPN type entities
     * @return a standard response form
     *         that contains the class name,
     *         functions, status and text
     */
    public ResponseForm update(
            Long id,
            String title,
            String description,
            String ip,
            String publicKey,
            NetEntity netEntity,
            Set<VpnTypeEntity> vpnTypeEntity) {
        super.function(update);

        // DEFAULTS
        title = title == null
                ? ""
                : title;
        description = description == null
                ? ""
                : description;
        publicKey = publicKey == null
                ? ""
                : publicKey;

        // CHECKINGS
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
                        !netEntityRepository.checkUniqueSubnet(netEntity.getSubnet())
                                ? "Net with ID " + netEntity.getId() + " not found"
                                : "")
                .append(
                        vpnTypeEntity.size() == 0
                                ? setLess("VPN type entities", 1)
                                : "");

        // CHECK FOR EXISTING
        VpnServerEntity entity = entityRepository.findById(id).orElse(null);
        err
                .append(
                        entity == null
                                ? "VPN server with ID " + id + " not found"
                                : "");
        if (err.length() > 0)
            return super.error(
                    err.toString());

        // CHECK FOR EQUAL
        err
                .append(
                        entity.getTitle().equals(title)
                                && entity.getDescription().equals(description)
                                && entity.getIp().equals(ip)
                                && entity.getPublicKey().equals(publicKey)
                                && entity.getNetEntity().getId().equals(netEntity.getId())
                                && entity.getVpnTypeEntity().equals(vpnTypeEntity)
                                        ? "All params of " + entity.getIp() + " is equal"
                                        : "");
        if (err.length() > 0)
            return super.error(
                    err.toString());

        // SAVINGS
        entity.setTitle(title);
        entity.setDescription(description);
        entity.setIp(ip);
        entity.setPublicKey(publicKey);
        entity.setNetEntity(netEntity);
        entity.setVpnTypeEntity(vpnTypeEntity);
        entityRepository.save(entity);

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

        return super.success(
                updated(
                        "VPN server",
                        entity.getIp()));
    }

    public ResponseForm delete() {
        super.function(delete);
        return super.success("Deleted VPN server: ");
    }

}
