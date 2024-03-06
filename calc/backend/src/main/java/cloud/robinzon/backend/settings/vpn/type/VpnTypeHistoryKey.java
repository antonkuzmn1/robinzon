package cloud.robinzon.backend.settings.vpn.type;

import java.io.Serializable;
import java.security.Timestamp;

public class VpnTypeHistoryKey implements Serializable {

    private VpnTypeEntity vpnTypeEntity;
    private Timestamp timestamp;

    public VpnTypeHistoryKey() {
    }

    public VpnTypeHistoryKey(
            VpnTypeEntity vpnTypeEntity,
            Timestamp timestamp) {
        this.vpnTypeEntity = vpnTypeEntity;
        this.timestamp = timestamp;
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

    @Override
    public String toString() {
        return "VpnTypeHistoryKey [vpnTypeEntity=" + vpnTypeEntity
                + ", timestamp=" + timestamp
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vpnTypeEntity == null) ? 0 : vpnTypeEntity.hashCode());
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
        VpnTypeHistoryKey other = (VpnTypeHistoryKey) obj;
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
        return true;
    }

}
