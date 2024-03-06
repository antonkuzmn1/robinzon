package cloud.robinzon.backend.settings.vpn.type;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import cloud.robinzon.backend.security.user.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "settings_vpn_type_history")
@IdClass(VpnTypeHistoryKey.class)
public class VpnTypeHistory {

    @Id
    @ManyToOne
    @JoinColumn
    private VpnTypeEntity vpnTypeEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @Column(nullable = false, length = 20)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    public VpnTypeHistory(
            VpnTypeEntity vpnTypeEntity,
            String name,
            UserEntity changeBy) {
        this.vpnTypeEntity = vpnTypeEntity;
        this.name = name;
        this.changeBy = changeBy;
    }

    public VpnTypeEntity getVpnTypeEntity() {
        return vpnTypeEntity;
    }

    public void setVpnTypeEntity(VpnTypeEntity vpnTypeEntity) {
        this.vpnTypeEntity = vpnTypeEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(UserEntity changeBy) {
        this.changeBy = changeBy;
    }

    @Override
    public String toString() {
        return "VpnTypeHistory [vpnTypeEntity=" + vpnTypeEntity
                + ", timestamp=" + timestamp
                + ", name=" + name
                + ", changeBy=" + changeBy
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vpnTypeEntity == null) ? 0 : vpnTypeEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((changeBy == null) ? 0 : changeBy.hashCode());
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
        VpnTypeHistory other = (VpnTypeHistory) obj;
        if (vpnTypeEntity == null) {
            if (other.vpnTypeEntity != null)
                return false;
        } else if (!vpnTypeEntity.equals(other.vpnTypeEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (changeBy == null) {
            if (other.changeBy != null)
                return false;
        } else if (!changeBy.equals(other.changeBy))
            return false;
        return true;
    }

}
