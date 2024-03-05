package cloud.robinzon.backend.db.fm;

import java.io.Serializable;
import java.sql.Timestamp;

public class FmHistoryKey implements Serializable {

    private FmEntity fmEntity;
    private Timestamp timestamp;

    public FmHistoryKey(
            FmEntity fmEntity,
            Timestamp timestamp) {
        this.fmEntity = fmEntity;
        this.timestamp = timestamp;
    }

    public FmHistoryKey() {
    }

    public FmEntity getFmEntity() {
        return fmEntity;
    }

    public void setFmEntity(FmEntity fmEntity) {
        this.fmEntity = fmEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "FmHistoryKey [fmEntity=" + fmEntity
                + ", timestamp=" + timestamp + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fmEntity == null) ? 0 : fmEntity.hashCode());
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
        FmHistoryKey other = (FmHistoryKey) obj;
        if (fmEntity == null) {
            if (other.fmEntity != null)
                return false;
        } else if (!fmEntity.equals(other.fmEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        return true;
    }

}