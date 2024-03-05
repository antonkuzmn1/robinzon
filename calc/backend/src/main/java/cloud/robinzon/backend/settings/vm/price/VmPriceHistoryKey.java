package cloud.robinzon.backend.settings.vm.price;

import java.io.Serializable;
import java.sql.Timestamp;

public class VmPriceHistoryKey implements Serializable {

    private VmPriceEntity vmPriceEntity;
    private Timestamp timestamp;

    public VmPriceHistoryKey() {
    }

    public VmPriceHistoryKey(
            VmPriceEntity vmPriceEntity,
            Timestamp timestamp) {
        this.vmPriceEntity = vmPriceEntity;
        this.timestamp = timestamp;
    }

    public VmPriceEntity getVmPriceEntity() {
        return vmPriceEntity;
    }

    public void setVmPriceEntity(VmPriceEntity vmPriceEntity) {
        this.vmPriceEntity = vmPriceEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "VmPriceHistoryKey [vmPriceEntity=" + vmPriceEntity
                + ", timestamp=" + timestamp + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vmPriceEntity == null) ? 0 : vmPriceEntity.hashCode());
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
        VmPriceHistoryKey other = (VmPriceHistoryKey) obj;
        if (vmPriceEntity == null) {
            if (other.vmPriceEntity != null)
                return false;
        } else if (!vmPriceEntity.equals(other.vmPriceEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        return true;
    }
}
