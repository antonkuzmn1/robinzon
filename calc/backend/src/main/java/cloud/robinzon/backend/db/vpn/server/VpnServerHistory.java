package cloud.robinzon.backend.db.vpn.server;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cloud.robinzon.backend.db.net.NetEntity;
import cloud.robinzon.backend.security.user.UserEntity;
import cloud.robinzon.backend.settings.vpn.type.VpnTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "vpn_server_history")
@IdClass(VpnServerHistoryKey.class)
public class VpnServerHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VpnServerEntity vpnServerEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 50)
    private String title;

    @Column(nullable = false, length = 255)
    private String description;

    @Column(nullable = false, length = 15)
    private String ip;

    @Column(nullable = false, length = 50)
    private String publicKey;

    @ManyToOne
    @JoinColumn(nullable = false)
    private NetEntity netEntity;

    @ManyToMany
    @JsonIgnoreProperties("vpnServerHistory")
    @JoinTable(name = "vpn_server_history_m2m_vpn_type")
    private Set<VpnTypeEntity> vpnTypeEntity = new HashSet<>();

    @ManyToOne
    @JoinColumn
    private UserEntity changeBy;

    @Column(nullable = false)
    private boolean deleted;

    /**
     * <h3>New entity</h3>
     *
     * @param vpnServerEntity
     * @param title
     * @param description
     * @param ip
     * @param publicKey
     * @param netEntity
     * @param vpnTypeEntity
     * @param changeBy
     * @param deleted
     */
    public VpnServerHistory(
            VpnServerEntity vpnServerEntity,
            String title,
            String description,
            String ip,
            String publicKey,
            NetEntity netEntity,
            Set<VpnTypeEntity> vpnTypeEntity,
            UserEntity changeBy,
            boolean deleted) {
        this.vpnServerEntity = vpnServerEntity;
        this.title = title;
        this.description = description;
        this.ip = ip;
        this.publicKey = publicKey;
        this.netEntity = netEntity;
        this.vpnTypeEntity = vpnTypeEntity;
        this.changeBy = changeBy;
        this.deleted = deleted;
    }

    /**
     * <h3>Delete entity</h3>
     *
     * @param vpnServerEntity
     * @param changeBy
     */
    public VpnServerHistory(
            VpnServerEntity vpnServerEntity,
            UserEntity changeBy) {
        this.vpnServerEntity = vpnServerEntity;
        this.changeBy = changeBy;
        this.title = vpnServerEntity.getTitle();
        this.description = vpnServerEntity.getDescription();
        this.ip = vpnServerEntity.getIp();
        this.publicKey = vpnServerEntity.getPublicKey();
        this.netEntity = vpnServerEntity.getNetEntity();
        this.vpnTypeEntity = vpnServerEntity.getVpnTypeEntity();
        this.deleted = true;
    }

    public VpnServerEntity getVpnServerEntity() {
        return vpnServerEntity;
    }

    public void setVpnServerEntity(VpnServerEntity vpnServerEntity) {
        this.vpnServerEntity = vpnServerEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public NetEntity getNetEntity() {
        return netEntity;
    }

    public void setNetEntity(NetEntity netEntity) {
        this.netEntity = netEntity;
    }

    public Set<VpnTypeEntity> getVpnTypeEntity() {
        return vpnTypeEntity;
    }

    public void setVpnTypeEntity(Set<VpnTypeEntity> vpnTypeEntity) {
        this.vpnTypeEntity = vpnTypeEntity;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(UserEntity changeBy) {
        this.changeBy = changeBy;
    }

    @Override
    public String toString() {
        return "VpnServerHistory [vpnServerEntity=" + vpnServerEntity
                + ", timestamp=" + timestamp
                + ", title=" + title
                + ", description=" + description
                + ", ip=" + ip
                + ", publicKey=" + publicKey
                + ", netEntity=" + netEntity
                + ", vpnTypeEntity=" + vpnTypeEntity
                + ", changeBy=" + changeBy
                + ", deleted=" + deleted
                + "]";
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vpnServerEntity == null) ? 0 : vpnServerEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + ((description == null) ? 0 : description.hashCode());
        result = prime * result + ((ip == null) ? 0 : ip.hashCode());
        result = prime * result + ((publicKey == null) ? 0 : publicKey.hashCode());
        result = prime * result + ((netEntity == null) ? 0 : netEntity.hashCode());
        result = prime * result + ((vpnTypeEntity == null) ? 0 : vpnTypeEntity.hashCode());
        result = prime * result + ((changeBy == null) ? 0 : changeBy.hashCode());
        result = prime * result + (deleted ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VpnServerHistory other = (VpnServerHistory) obj;
        if (vpnServerEntity == null) {
            if (other.vpnServerEntity != null)
                return false;
        } else if (!vpnServerEntity.equals(other.vpnServerEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (description == null) {
            if (other.description != null)
                return false;
        } else if (!description.equals(other.description))
            return false;
        if (ip == null) {
            if (other.ip != null)
                return false;
        } else if (!ip.equals(other.ip))
            return false;
        if (publicKey == null) {
            if (other.publicKey != null)
                return false;
        } else if (!publicKey.equals(other.publicKey))
            return false;
        if (netEntity == null) {
            if (other.netEntity != null)
                return false;
        } else if (!netEntity.equals(other.netEntity))
            return false;
        if (vpnTypeEntity == null) {
            if (other.vpnTypeEntity != null)
                return false;
        } else if (!vpnTypeEntity.equals(other.vpnTypeEntity))
            return false;
        if (changeBy == null) {
            if (other.changeBy != null)
                return false;
        } else if (!changeBy.equals(other.changeBy))
            return false;
        if (deleted != other.deleted)
            return false;
        return true;
    }

}