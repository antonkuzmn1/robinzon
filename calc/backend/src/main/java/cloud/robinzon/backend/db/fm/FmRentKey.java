package cloud.robinzon.backend.db.fm;

import java.io.Serializable;
import java.sql.Timestamp;

import cloud.robinzon.backend.db.client.ClientEntity;

public class FmRentKey implements Serializable {

    private FmEntity fmEntity;
    private ClientEntity clientEntity;
    private Timestamp timestamp;

    public FmRentKey() {
    }

    public FmRentKey(
            FmEntity fmEntity,
            ClientEntity clientEntity,
            Timestamp timestamp) {
        this.fmEntity = fmEntity;
        this.clientEntity = clientEntity;
        this.timestamp = timestamp;
    }

    public FmEntity getFmEntity() {
        return fmEntity;
    }

    public void setFmEntity(FmEntity fmEntity) {
        this.fmEntity = fmEntity;
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
        return "FmRentKey [fmEntity=" + fmEntity
                + ", clientEntity=" + clientEntity
                + ", timestamp=" + timestamp
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fmEntity == null) ? 0 : fmEntity.hashCode());
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
        FmRentKey other = (FmRentKey) obj;
        if (fmEntity == null) {
            if (other.fmEntity != null)
                return false;
        } else if (!fmEntity.equals(other.fmEntity))
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