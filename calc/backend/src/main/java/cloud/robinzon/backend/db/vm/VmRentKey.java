package cloud.robinzon.backend.db.vm;

import java.sql.Timestamp;

import cloud.robinzon.backend.db.client.ClientEntity;

public class VmRentKey {

    private VmEntity vmEntity;
    private ClientEntity clientEntity;
    private Timestamp timestamp;

    public VmRentKey(
            VmEntity vmEntity,
            ClientEntity clientEntity,
            Timestamp timestamp) {
        this.vmEntity = vmEntity;
        this.clientEntity = clientEntity;
        this.timestamp = timestamp;
    }

    public VmRentKey() {
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

    @Override
    public String toString() {
        return "VmRentKey [vmEntity=" + vmEntity
                + ", clientEntity=" + clientEntity
                + ", timestamp=" + timestamp
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vmEntity == null) ? 0 : vmEntity.hashCode());
        result = prime * result + ((clientEntity == null) ? 0 : clientEntity.hashCode());
        result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
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
        VmRentKey other = (VmRentKey) obj;
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
        return true;
    }

}