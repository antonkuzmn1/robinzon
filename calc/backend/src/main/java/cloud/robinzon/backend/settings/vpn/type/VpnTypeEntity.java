package cloud.robinzon.backend.settings.vpn.type;

import java.security.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import cloud.robinzon.backend.db.vpn.server.VpnServerEntity;
import cloud.robinzon.backend.db.vpn.server.VpnServerHistory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "settings_vpn_type_entity")
@EntityListeners(VpnTypeListener.class)
public class VpnTypeEntity {

    @Id
    private Long id;

    @Column(nullable = false, length = 20)
    private String name;

    @UpdateTimestamp
    private Timestamp timestamp;

    @Column(nullable = false)
    private boolean deleted;

    @ManyToMany(mappedBy = "vpnTypeEntity")
    @JsonIgnoreProperties("vpnTypeEntity")
    private Set<VpnServerEntity> vpnServerEntity = new HashSet<>();

    @ManyToMany(mappedBy = "vpnTypeEntity")
    @JsonIgnoreProperties("vpnTypeEntity")
    private Set<VpnServerHistory> vpnServerHistory = new HashSet<>();

    public VpnTypeEntity(
            Long id,
            String name,
            boolean deleted,
            Set<VpnServerEntity> vpnServerEntity) {
        this.id = id;
        this.name = name;
        this.deleted = deleted;
        this.vpnServerEntity = vpnServerEntity;
    }

    public VpnTypeEntity(
            String name,
            Set<VpnServerEntity> vpnServerEntity) {
        this.name = name;
        this.vpnServerEntity = vpnServerEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Set<VpnServerEntity> getVpnServerEntity() {
        return vpnServerEntity;
    }

    public void setVpnServerEntity(Set<VpnServerEntity> vpnServerEntity) {
        this.vpnServerEntity = vpnServerEntity;
    }

    @Override
    public String toString() {
        return "VpnTypeEntity [id=" + id
                + ", name=" + name
                + ", timestamp=" + timestamp
                + ", deleted=" + deleted
                + ", vpnServerEntity=" + vpnServerEntity
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + (deleted ? 1231 : 1237);
        result = prime * result + ((vpnServerEntity == null) ? 0 : vpnServerEntity.hashCode());
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
        VpnTypeEntity other = (VpnTypeEntity) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (deleted != other.deleted)
            return false;
        if (vpnServerEntity == null) {
            if (other.vpnServerEntity != null)
                return false;
        } else if (!vpnServerEntity.equals(other.vpnServerEntity))
            return false;
        return true;
    }

}