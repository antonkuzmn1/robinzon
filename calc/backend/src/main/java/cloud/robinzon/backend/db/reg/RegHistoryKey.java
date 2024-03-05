package cloud.robinzon.backend.db.reg;

import java.io.Serializable;
import java.sql.Timestamp;

public class RegHistoryKey implements Serializable {

    private RegEntity regEntity;
    private Timestamp timestamp;

    public RegHistoryKey(
            RegEntity regEntity,
            Timestamp timestamp) {
        this.regEntity = regEntity;
        this.timestamp = timestamp;
    }

    public RegHistoryKey() {
    }

    public RegEntity getRegEntity() {
        return regEntity;
    }

    public void setRegEntity(RegEntity regEntity) {
        this.regEntity = regEntity;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "RegHistoryKey [regEntity=" + regEntity
                + ", timestamp=" + timestamp
                + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((regEntity == null) ? 0 : regEntity.hashCode());
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
        RegHistoryKey other = (RegHistoryKey) obj;
        if (regEntity == null) {
            if (other.regEntity != null)
                return false;
        } else if (!regEntity.equals(other.regEntity))
            return false;
        if (timestamp == null) {
            if (other.timestamp != null)
                return false;
        } else if (!timestamp.equals(other.timestamp))
            return false;
        return true;
    }

}