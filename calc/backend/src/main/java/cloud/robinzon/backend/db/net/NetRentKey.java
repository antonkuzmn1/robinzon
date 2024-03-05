package cloud.robinzon.backend.db.net;

import java.io.Serializable;
import java.sql.Timestamp;

import cloud.robinzon.backend.db.client.ClientEntity;

public class NetRentKey implements Serializable {

    private NetEntity netEntity;
    private ClientEntity clientEntity;
    private Timestamp timestamp;

    public NetRentKey(
            NetEntity netEntity,
            ClientEntity clientEntity) {
        this.netEntity = netEntity;
        this.clientEntity = clientEntity;
    }

    public NetRentKey() {
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

    @Override
    public String toString() {
        return "NetRentKey [netEntity=" + netEntity
                + ", clientEntity=" + clientEntity
                + ", timestamp=" + timestamp
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((netEntity == null) ? 0 : netEntity.hashCode());
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
        NetRentKey other = (NetRentKey) obj;
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
        return true;
    }

}
