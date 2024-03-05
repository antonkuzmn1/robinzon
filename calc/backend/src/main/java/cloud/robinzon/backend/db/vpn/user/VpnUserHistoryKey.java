package cloud.robinzon.backend.db.vpn.user;

import java.io.Serializable;
import java.sql.Timestamp;

public class VpnUserHistoryKey implements Serializable {

    private VpnUserEntity vpnUserEntity;
    private Timestamp timestamp;

    public VpnUserHistoryKey(
            VpnUserEntity vpnUserEntity,
            Timestamp timestamp) {
        this.vpnUserEntity = vpnUserEntity;
        this.timestamp = timestamp;
    }

    public VpnUserHistoryKey() {
    }

    public VpnUserEntity getVpnUserEntity() {
        return vpnUserEntity;
    }

    public void setVpnUserEntity(VpnUserEntity vpnUserEntity) {
        this.vpnUserEntity = vpnUserEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "VpnUserHistoryKey [vpnUserEntity=" + vpnUserEntity
                + ", timestamp=" + timestamp + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vpnUserEntity == null) ? 0 : vpnUserEntity.hashCode());
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
        VpnUserHistoryKey other = (VpnUserHistoryKey) obj;
        if (vpnUserEntity == null) {
            if (other.vpnUserEntity != null)
                return false;
        } else if (!vpnUserEntity.equals(other.vpnUserEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        return true;
    }

}
