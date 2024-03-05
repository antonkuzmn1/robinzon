package cloud.robinzon.backend.db.vm;

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
@Table(name = "vm_rent")
@IdClass(VmRentKey.class)
public class VmRent {

    @Id
    @ManyToOne
    @JoinColumn
    private VmEntity vmEntity;

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

    public VmRent(
            VmEntity vmEntity,
            ClientEntity clientEntity,
            UserEntity changeBy) {
        this.vmEntity = vmEntity;
        this.clientEntity = clientEntity;
        this.changeBy = changeBy;
    }

    public VmRent() {
    }

    public VmEntity getVmEntity() {
        return vmEntity;
    }

    public void setVmEntity(VmEntity vmEntity) {
        this.vmEntity = vmEntity;
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
        return "VmRent [vmEntity=" + vmEntity
                + ", clientEntity=" + clientEntity
                + ", timestamp=" + timestamp
                + ", changeBy=" + changeBy
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vmEntity == null) ? 0 : vmEntity.hashCode());
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
        VmRent other = (VmRent) obj;
        if (vmEntity == null) {
            if (other.vmEntity != null)
                return false;
        } else if (!vmEntity.equals(other.vmEntity))
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