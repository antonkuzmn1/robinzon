package cloud.robinzon.backend.db.vpn.server;

import java.io.Serializable;
import java.sql.Timestamp;

public class VpnServerHistoryKey
        implements Serializable {

    private VpnServerEntity vpnServerEntity;
    private Timestamp timestamp;

    public VpnServerHistoryKey(
            VpnServerEntity vpnServerEntity,
            Timestamp timestamp) {
        this.vpnServerEntity = vpnServerEntity;
        this.timestamp = timestamp;
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

    @Override
    public String toString() {
        return "VpnServerHistoryKey [vpnServerEntity=" + vpnServerEntity
                + ", timestamp=" + timestamp + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((vpnServerEntity == null) ? 0 : vpnServerEntity.hashCode());
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
        VpnServerHistoryKey other = (VpnServerHistoryKey) obj;
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
        return true;
    }

}
