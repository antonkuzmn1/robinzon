package cloud.robinzon.backend.db.vm;

import java.io.Serializable;
import java.sql.Timestamp;

public class VmHistoryKey implements Serializable {

    private VmEntity vmEntity;
    private Timestamp timestamp;

    public VmHistoryKey() {
    }

    public VmHistoryKey(
            VmEntity vmEntity,
            Timestamp timestamp) {
        this.vmEntity = vmEntity;
        this.timestamp = timestamp;
    }

    public VmEntity getVmEntity() {
        return vmEntity;
    }

    public void setVmEntity(VmEntity vmEntity) {
        this.vmEntity = vmEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "VmHistoryKey [vmEntity=" + vmEntity
                + ", timestamp=" + timestamp + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vmEntity == null) ? 0 : vmEntity.hashCode());
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
        VmHistoryKey other = (VmHistoryKey) obj;
        if (vmEntity == null) {
            if (other.vmEntity != null)
                return false;
        } else if (!vmEntity.equals(other.vmEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        return true;
    }

}