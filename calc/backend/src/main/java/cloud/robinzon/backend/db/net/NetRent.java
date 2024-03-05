package cloud.robinzon.backend.db.net;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import cloud.robinzon.backend.db.client.ClientEntity;
import cloud.robinzon.backend.security.user.UserEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "net_rent")
@IdClass(NetRentKey.class)
public class NetRent {

    @Id
    @ManyToOne
    @JoinColumn
    private NetEntity netEntity;

    @Id
    @ManyToOne
    @JoinColumn
    private ClientEntity clientEntity;

    @Id
    @CreationTimestamp
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity changeBy;

    public NetRent(
            NetEntity netEntity,
            ClientEntity clientEntity,
            UserEntity changeBy) {
        this.netEntity = netEntity;
        this.clientEntity = clientEntity;
        this.changeBy = changeBy;
    }

    public NetEntity getNetEntity() {
        return netEntity;
    }

    public void setNetEntity(NetEntity netEntity) {
        this.netEntity = netEntity;
    }

    public ClientEntity getClientEntity() {
        return clientEntity;
    }

    public void setClientEntity(ClientEntity clientEntity) {
        this.clientEntity = clientEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public UserEntity getChangeBy() {
        return changeBy;
    }

    public void setChangeBy(UserEntity changeBy) {
        this.changeBy = changeBy;
    }

    @Override
    public String toString() {
        return "NetRent [netEntity=" + netEntity
                + ", clientEntity=" + clientEntity
                + ", timestamp=" + timestamp
                + ", changeBy=" + changeBy
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((netEntity == null) ? 0 : netEntity.hashCode());
        result = prime * result + ((clientEntity == null) ? 0 : clientEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
        NetRent other = (NetRent) obj;
        if (netEntity == null) {
            if (other.netEntity != null)
                return false;
        } else if (!netEntity.equals(other.netEntity))
            return false;
        if (clientEntity == null) {
            if (other.clientEntity != null)
                return false;
        } else if (!clientEntity.equals(other.clientEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        if (changeBy == null) {
            if (other.changeBy != null)
                return false;
        } else if (!changeBy.equals(other.changeBy))
            return false;
        return true;
    }

}