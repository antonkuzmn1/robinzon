package cloud.robinzon.backend.db.net;

import java.io.Serializable;
import java.sql.Timestamp;

public class NetHistoryKey implements Serializable {

    private NetEntity netEntity;
    private Timestamp timestamp;

    public NetHistoryKey(
            NetEntity netEntity,
            Timestamp timestamp) {
        this.netEntity = netEntity;
        this.timestamp = timestamp;
    }

    public NetHistoryKey() {
    }

    public NetEntity getNetEntity() {
        return netEntity;
    }

    public void setNetEntity(NetEntity netEntity) {
        this.netEntity = netEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "NetHistoryKey [netEntity=" + netEntity
                + ", timestamp=" + timestamp
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((netEntity == null) ? 0 : netEntity.hashCode());
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
        NetHistoryKey other = (NetHistoryKey) obj;
        if (netEntity == null) {
            if (other.netEntity != null)
                return false;
        } else if (!netEntity.equals(other.netEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        return true;
    }

}
